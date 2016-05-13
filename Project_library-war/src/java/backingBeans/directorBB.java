/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backingBeans;

import beans.LibDirectorLocal;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;

/**
 *
 * @author Nudista
 */
@Named(value = "directorBB")
@RequestScoped
public class directorBB {

    @EJB
    private LibDirectorLocal libDirector;
    private String masterpass, name, surname, mail, pass, userchange, bookchange, isbn, count;

    /**
     * Creates a new instance of directorBB
     */
    public directorBB() {

    }

    public String getMasterpass() {
        return masterpass;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public void setMasterpass(String masterpass) {
        this.masterpass = masterpass;
    }

    public String getUserchange() {
        return userchange;
    }

    public void setUserchange(String userchange) {
        this.userchange = userchange;
    }

    public String getBookchange() {
        return bookchange;
    }

    public void setBookchange(String bookchange) {
        this.bookchange = bookchange;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public void addAdmin() {
        libDirector.addAdminUser(name, surname, mail, pass, true);
        userchange = "Admin user added: " + mail;
        bookchange = "";
    }

    public void deleteUser() {
        boolean result;
        result = libDirector.deleteAdminUser(mail);
        userchange = "User deleted: " + mail;
        bookchange = "";
        if(!result){
          userchange = "User " + mail + " wans't found";  
        }
    }

    public void deleteBook() {
        boolean result;
        result = libDirector.deleteBook(isbn);
        userchange = "";
        bookchange = "Book deleted: " + isbn;
        System.out.println(result);
        if(!result){
          bookchange = "Book " + isbn + " wans't found";  
        }
    }

    public void changeBook() {
        boolean result;
        if("".equals(count)){
            count="0";
        }
        result = libDirector.changeCountBooks(isbn, Integer.parseInt(count));
        userchange = "";
        bookchange = "Number of books " + isbn + " changed to " + count;
        if(!result){
          bookchange = "Book " + isbn + " wans't found";  
        }
    }
}
