/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import entities.Genre;
import java.util.Date;
import javax.ejb.Local;

/**
 *
 * @author Nudista
 */
@Local
public interface StaffSesBeanLocal {

    public void addLibItem(int count, Genre g, String author, int pages, String isbn, String bookname);

    public void addUser(String name, String surname, String mail, String pass);

    void closeOrder(String user, Date orderDate);

    String getPassword(String usermail);
}
