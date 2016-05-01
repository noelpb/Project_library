/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package library;

import entities.Genre;
import javax.ejb.Local;

/**
 *
 * @author Nudista
 */
@Local
public interface StaffSesBeanLocal {

    
   public boolean addLibItem(int count, Genre g, String author, int pages, String isbn, String bookname); 
}
