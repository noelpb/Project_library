/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backingBeans;

import beans.CustomerBeanLocal;
import beans.findBookBeanLocal;
import entities.BookItem;
import entities.LibraryItem;
import entities.Orders;
import entities.WaitingListItem;
import java.io.IOException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.component.html.HtmlDataTable;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

/**
 *
 * @author Nudista
 */
@Named(value = "customerBB")
@SessionScoped
public class customerBB implements Serializable {

    @EJB
    private CustomerBeanLocal customerBean;
    private String question, user, pass, url, result;

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }
    private HtmlDataTable datatableBooks;
    @EJB
    private findBookBeanLocal findBookBean;
    private String name, author, isbn, genre;
    private List<LibraryItem> resultList;

    /**
     * Creates a new instance of customerBB
     */
    public customerBB() {
        result = "";

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public List<LibraryItem> getResultList() {
        return resultList;
    }

    public void setResultList(List<LibraryItem> resultList) {
        this.resultList = resultList;
    }

    public HtmlDataTable getDatatableBooks() {
        return datatableBooks;
    }

    public void setDatatableBooks(HtmlDataTable datatableBooks) {
        this.datatableBooks = datatableBooks;
    }

    public CustomerBeanLocal getCustomerBean() {
        return customerBean;
    }

    public void setCustomerBean(CustomerBeanLocal customerBean) {
        this.customerBean = customerBean;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public void findByName() {
        resultList = findBookBean.findName(name);
    }

    public void findByAuthor() {
        resultList = findBookBean.findAuthor(author);
    }

    public void findByISBN() {
        resultList = findBookBean.findISBN(isbn);
    }

    public void findByGenre() {
        resultList = findBookBean.findGenre(genre);
    }

    public void findAll() {
        resultList = findBookBean.selectAll();
    }
    
    public void sendQuestion(){
        customerBean.sendQuestion(this.question, this.user);
    }

    public String log() {
        String passFromDatabase = customerBean.getPassword(user);
        FacesContext context = FacesContext.getCurrentInstance();
        if (passFromDatabase == null) {
            context.addMessage(null, new FacesMessage("Unknown username, try again"));
            result = "Unknown username, try it again";
            user = null;
            pass = null;
            return "customerOrder?faces-redirect=truee";
        }
        if (passFromDatabase.equals(pass)) {
            context.getExternalContext().getSessionMap().put("user", user);
            result="";
            return "cart?faces-redirect=truee";           
        } else {
            context.addMessage(null, new FacesMessage("Wrong password, try again"));
            user = null;
            pass = null;
            result = "Wrong password, try it again";
            return "customerOrder?faces-redirect=truee";
        }

    }
    
    public boolean emptyCart(){
      int i =  customerBean.getMyCart().size();
      if(i==0){
          return true;
      }else{
          return false;
      }
    }

    public void addToCart() {
        LibraryItem book = (LibraryItem) datatableBooks.getRowData();
        customerBean.addToCart(book);
        result = "Book " + book.getBookISBN().getISBN() + " was added to your cart.";
    }

    public void addToWishlist() {
        LibraryItem book = (LibraryItem) datatableBooks.getRowData();
        customerBean.addToWaitingList(book.getBookISBN().getISBN(), user);
        result = "Book " + book.getBookISBN().getISBN() + " was added to your wishlist.";
    }

    public String next() {
        result = "";
        return "cartFinal?faces-redirect=truee";
    }

    public String finishIt() {
        customerBean.finishOrder(user);
        return "confirmation?faces-redirect=truee";
    }

    public List<Orders> getAllMyOrders() {
        return customerBean.getAllMyOrders(user);
    }

    public List<WaitingListItem> getAllMyWaiting() {
        return customerBean.getAllMyWishlists(user);
    }

    public String getBooksFromOrder(String id) {
        return customerBean.getBooksFromOrder(id);
    }

    public void logout() {
        user = null;
        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        ec.invalidateSession();
        try {
            ec.redirect("./index.html");
        } catch (IOException ex) {
            Logger.getLogger(customerBB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
