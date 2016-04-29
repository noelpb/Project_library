/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package library;

import entities.BookItem;
import javax.ejb.Local;

/**
 *
 * @author Nudista
 */
@Local
interface LocalLibSessionBean {
    
    boolean addProductToCart(BookItem b);

    boolean removeProductFromCart(BookItem b);
    
    boolean checkOut();
   
}
