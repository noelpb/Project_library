/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import entities.Users;
import javax.ejb.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Nudista
 */
@Singleton
public class LibDirector implements LibDirectorLocal {
    private final String masterpass="director";

    private Users u;
    @PersistenceContext(unitName = "lPU")
    private EntityManager em;

    @Override
    public void addAdminUser(String name, String surname, String mail, String pass, boolean admin) {
         u = new Users(name, surname, mail, pass, admin);
        em.persist(u);
    }

    @Override
    public void deleteBook(String ISBN) {
        
        
    }

    @Override
    public void changeCountBooks(String ISBN, int count) {
        
        
    }
    
    

}
