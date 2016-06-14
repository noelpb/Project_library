/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clients;

import Client.GetAllBooks;
import Client.GetBooks;
import Client.GetBooks_Service;

/**
 *
 * @author Noel
 */
public class SOAPCLient {
    public static void main(String[] args) {
        GetBooks_Service gbooks = new GetBooks_Service();
        final GetAllBooks proxy = gbooks.getGetAllBooksPort();
        
        System.out.println(proxy.getBooks());
    }
    
}
