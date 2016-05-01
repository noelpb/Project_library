/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package library;

import entities.LibraryItem;
import entities.Users;
import javax.ejb.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

/**
 *
 * @author Nudista
 */
@Singleton
public class LibDirector implements LibDirectorLocal {

    EntityManagerFactory emf;
    EntityManager em;

    @Override
    public boolean deleteBook(LibraryItem book) {
        LibraryItem it = null;
        emf = Persistence.createEntityManagerFactory("manager");
        em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        em.remove(book);
        tx.commit();
        it = em.find(LibraryItem.class, book);
        em.close();
        emf.close();
        return it == null;
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @Override
    public void addUser(Users user) {
        emf = Persistence.createEntityManagerFactory("manager");
        em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        em.persist(user);
        tx.commit();
        em.close();
        emf.close();
    }

    @Override
    public boolean deleteUser(Users user) {
        Users it = null;
        emf = Persistence.createEntityManagerFactory("manager");
        em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        em.remove(user);
        tx.commit();
        it = em.find(Users.class, user);
        em.close();
        emf.close();
        return it == null;
    }

}
