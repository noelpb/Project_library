/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import entities.BookItem;
import entities.LibraryItem;
import entities.Orders;
import entities.WaitingListItem;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Nudista
 */
@Local
public interface CustomerBeanLocal {

    boolean addToCart(LibraryItem b);

    void addToWaitingList(String isbn, String user);

    void finishOrder(String user);

    String getPassword(String usermail);

    List<LibraryItem> getMyCart();

    List<Orders> getAllMyOrders(String user);
    
    List<WaitingListItem> getAllMyWishlists(String user);

    String getBooksFromOrder(String id);
}
