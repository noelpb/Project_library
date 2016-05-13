/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import javax.ejb.Local;

/**
 *
 * @author Nudista
 */
@Local
public interface LibDirectorLocal {

    void addAdminUser(String name, String surname, String mail, String pass, boolean admin);

    boolean deleteAdminUser(String mail);

    boolean deleteBook(String ISBN);

    boolean changeCountBooks(String ISBN, int count);

}
