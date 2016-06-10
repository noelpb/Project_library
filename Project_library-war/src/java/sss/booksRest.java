package sss;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import beans.getBooks;
import beans.findBookBean;
import entities.LibraryItem;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

/**
 *
 * @author Noel
 */
@Path("books")

public class booksRest {
    @EJB private getBooks books;
    @GET
    @Produces("text/plain")
    public String getHtml(){
     return books.getBooks();
    }
    @GET
    @Path("{id}")
    @Produces("text/plain")
    public String getMessageById(@PathParam("id") int id){
        return books.getBookById(id);
    }
}

