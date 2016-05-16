/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import entities.AuthorItem;
import entities.BookItem;
import entities.Genre;
import entities.LibraryItem;
import entities.Orders;
import entities.Users;
import java.util.Date;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author Nudista
 */
@Stateless
public class StaffSesBean implements StaffSesBeanLocal {

    private AuthorItem a;
    private BookItem b;
    private LibraryItem l;
    private Users u;
    @PersistenceContext(unitName = "lPU")
    private EntityManager em;

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @Override
    public boolean addLibItem(int count, Genre g, String author, int pages, String isbn, String bookname) {
        TypedQuery<AuthorItem> query;
        TypedQuery<LibraryItem> query2;
        a = null;
        l = null;
        try {
            query = em.createQuery("SELECT a FROM AuthorItem  a WHERE a.name LIKE :name", AuthorItem.class);
            a = query.setParameter("name", author).getSingleResult();
        } catch (NoResultException e) {
            a = new AuthorItem(author);
        }
        try {
            query2 = em.createQuery("SELECT l FROM LibraryItem l WHERE l.bookISBN.ISBN LIKE :name", LibraryItem.class);
            l = query2.setParameter("name", isbn).getSingleResult();
        } catch (NoResultException e) {
            l = null;
        }
        if (l != null) {
            return false;
        }
        b = new BookItem(bookname, pages, isbn, g);
        b.getWriter().add(a);
        a.getBook().add(b);
        l = new LibraryItem(b, count);
        em.persist(l);
        em.persist(b);
        em.persist(a);
        return true;
    }

    @Override
    public boolean addUser(String name, String surname, String mail, String pass) {
        TypedQuery<Users> query;
        Users us;
        try {
            query = em.createQuery("SELECT u FROM Users  u WHERE u.mail LIKE :name", Users.class);
            us = query.setParameter("name", mail).getSingleResult();
        } catch (NoResultException e) {
            us = null;
        }
        if (us == null) {
            u = new Users(name, surname, mail, pass);
            em.persist(u);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean closeOrder(String user, String orderDate) {
        Orders o;
        TypedQuery<Orders> query;
        Date ost = new Date(orderDate);
        System.out.println(ost);
        try {
            query = em.createQuery("SELECT o FROM Orders o INNER JOIN o.user u WHERE u.mail = :param AND o.startDate = :date", Orders.class);
            query.setParameter("date", ost);
            o = query.setParameter("param", user).getSingleResult();
        } catch (NoResultException | NonUniqueResultException ex) {
            return false;
        } 
        o.setOpenOrder(false);
        o.getLibItem().stream().forEach((object) -> {
            object.setAvailability(true);
        });
        return true;
    }

    @Override
    public String getPassword(String usermail) {
        Users s;
        try {
            TypedQuery<Users> query = em.createQuery("SELECT u FROM Users u WHERE u.mail = :param AND u.adminUser = TRUE", Users.class);
            s = query.setParameter("param", usermail).getSingleResult();
            return s.getPass();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public boolean deleteUser(String mail) {
        TypedQuery<Users> query;
        Users us;
        try {
            query = em.createQuery("SELECT u FROM Users  u WHERE u.mail LIKE :name", Users.class);
            us = query.setParameter("name", mail).getSingleResult();
        } catch (NoResultException e) {
            return false;
        }
        if (!us.getAdminUser()) {
            em.remove(us);
            return true;
        } else {
            return false;
        }
    }
}
