/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package library;

import entities.LibraryItem;
import entities.Users;
import javax.ejb.Local;

/**
 *
 * @author Nudista
 */
@Local
public interface LibDirectorLocal {

    boolean deleteBook(LibraryItem parameter);

    void addUser(Users user);

    boolean deleteUser(Users user);
    
}
