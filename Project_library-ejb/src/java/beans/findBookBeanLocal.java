/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import entities.LibraryItem;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Nudista
 */
@Local
public interface findBookBeanLocal {

   List<LibraryItem> findISBN(String ISBN);
   List<LibraryItem> findName(String book);
   List<LibraryItem> findAuthor(String author);

    List<LibraryItem> findGenre(String genre);

    List<LibraryItem> selectAll();
    
}
