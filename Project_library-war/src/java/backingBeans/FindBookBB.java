/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backingBeans;

import beans.findBookBeanLocal;
import entities.LibraryItem;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;

/**
 *
 * @author Nudista
 */
@Named(value = "findBookBB")
@RequestScoped
public class FindBookBB {

     @EJB
    private findBookBeanLocal findBookBean;
    private String name,author, isbn, genre;
    private List<LibraryItem> resultList;
   
     /**
     * Creates a new instance of findBookBean
     */
    public FindBookBB() {
    }

    public List<LibraryItem> getResultList() {
        return resultList;
    }

    public void setResultList(List<LibraryItem> resultList) {
        this.resultList = resultList;
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
    
    public void findByName(){
       resultList =  findBookBean.findName(name);
    }
    
    public void findByAuthor(){
       resultList =   findBookBean.findAuthor(author);
    }
    public void findByISBN(){
      resultList =    findBookBean.findISBN(isbn);
    }
    public void findByGenre(){
      resultList =    findBookBean.findGenre(genre);
    }
     public void findAll(){
      resultList =    findBookBean.selectAll();
    }
    
}
