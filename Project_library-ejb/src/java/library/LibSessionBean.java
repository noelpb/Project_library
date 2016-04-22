/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package library;


import book.Book;
import javax.ejb.LocalBean;
import javax.ejb.Stateful;

/**
 *
 * @author Noel
 */
@Stateful
@LocalBean
public class LibSessionBean implements LocalLibSessionBean{

    @Override
    public boolean addProductToCart(Book b) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean removeProductFromCart(Book b) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean checkOut() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    int i;
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
}
