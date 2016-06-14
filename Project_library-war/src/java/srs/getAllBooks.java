/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package srs;

import beans.getBooks;
import javax.ejb.EJB;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

/**
 *
 * @author Noel
 */
@WebService(serviceName = "GetBooks")
public class getAllBooks {

    @EJB
    private getBooks ejbRef;// Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Web Service Operation")

    @WebMethod(operationName = "getBooks")
    public String getBooks() {
        return ejbRef.getBooks();
    }

    @WebMethod(operationName = "getBookById")
    public String getBookById(@WebParam(name = "id") String id) {
        return ejbRef.getBookById(id);
    }
    
}
