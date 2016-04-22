/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package library;

import book.Book;
import javax.ejb.Local;

/**
 *
 * @author Nudista
 */
@Local
interface LocalLibSessionBean {
    
    boolean addProductToCart(Book b);

    boolean removeProductFromCart(Book b);
    
    boolean checkOut();
   
}
