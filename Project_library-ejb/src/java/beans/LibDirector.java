/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import entities.LibraryItem;
import entities.Users;
import javax.ejb.Singleton;
import javax.interceptor.Interceptors;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import timersandinterceptors.Interceptor;

/**
 *
 * @author Nudista
 */
@Singleton
@Interceptors(Interceptor.class)

public class LibDirector implements LibDirectorLocal {


    private Users u;
    @PersistenceContext(unitName = "lPU")
    private EntityManager em;

    @Override
    public void addAdminUser(String name, String surname, String mail, String pass, boolean admin) {
        u = new Users(name, surname, mail, pass, admin);
        em.persist(u);
    }

    @Override
    public boolean deleteBook(String ISBN) {
        TypedQuery<LibraryItem> query;
        LibraryItem i = null;
        boolean res;
        try {
            query = em.createQuery("SELECT l FROM LibraryItem  l WHERE l.bookISBN.ISBN LIKE :name", LibraryItem.class);
            i = query.setParameter("name", ISBN).getSingleResult();
            em.remove(i);
            res = true;
        } catch (NoResultException e) {
            res = false;
        }
        if (i == null) {
            res = false;
        }
        return res;
    }

    @Override
    public boolean changeCountBooks(String ISBN, int count) {
        Query query;
        boolean res;
        try {
            query = em.createQuery("UPDATE LibraryItem l SET l.count = :count WHERE l.bookISBN.ISBN LIKE :name");
            query.setParameter("count", count);
            query.setParameter("name", "%" + ISBN + "%");
            query.executeUpdate();
            return true;
        } catch (NoResultException e) {
            return false;
        }
    }

    @Override
    public boolean deleteAdminUser(String mail) {
        TypedQuery<Users> query;
        Users us = null;
        boolean res;
        try {
            query = em.createQuery("SELECT u FROM Users  u WHERE u.mail LIKE :name", Users.class);
            us = query.setParameter("name", mail).getSingleResult();
            em.remove(us);
            res= true;
        } catch (NoResultException e) {
            res= false;
        }
        if (us == null) {
            res = false;
        }
        return res;
    }

}
