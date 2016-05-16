/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import entities.LibraryItem;
import entities.Orders;
import entities.WaitingListItem;
import entities.Users;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;
import javax.annotation.PostConstruct;
import javax.ejb.Stateful;
import javax.ejb.StatefulTimeout;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 *
 * @author Nudista
 */
@Stateful
@StatefulTimeout(unit = TimeUnit.MINUTES, value = 15)
public class CustomerBean implements CustomerBeanLocal {

    private List<LibraryItem> cart;
    @PersistenceContext(unitName = "lPU")
    private EntityManager em;

    @PostConstruct
    private void init() {
        cart = new ArrayList<>();
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @Override
    public boolean addToCart(LibraryItem b) {
        for (LibraryItem bookItem : cart) {
            if (bookItem.getBookISBN().getISBN().equals(b.getBookISBN().getISBN())) {
                return false;
            }
        }
        cart.add(b);
        return true;
    }

    @Override
    public void addToWaitingList(String isbn, String user) {
        TypedQuery<Users> query;
        Users us;
        WaitingListItem wl;
        try {
            query = em.createQuery("SELECT u FROM Users  u WHERE u.mail LIKE :name", Users.class);
            us = query.setParameter("name", user).getSingleResult();

        } catch (NoResultException e) {
            return;
        }
        TypedQuery<LibraryItem> query2;
        LibraryItem l;
        try {
            query2 = em.createQuery("SELECT l FROM LibraryItem  l WHERE l.bookISBN.ISBN LIKE :name", LibraryItem.class);
            l = query2.setParameter("name", isbn).getSingleResult();
        } catch (NoResultException e) {
            return;
        }
        wl = new WaitingListItem();
        wl.getUser().add(us);
        us.setWaitlist(wl);
        wl.setLibId(l);
        l.setWaitingList(wl);
        em.persist(wl);

    }

    @Override
    public void finishOrder(String user) {
        TypedQuery<Users> query;
        TypedQuery<Orders> queryo;
        Users us;
        Orders order;
        Date dateO = new Date();
        try {
            queryo = em.createQuery("SELECT o FROM Orders o INNER JOIN o.user u WHERE u.mail LIKE :usernam AND o.startDate = :dateO AND o.openOrder = TRUE", Orders.class);
            queryo.setParameter("dateO", dateO);
            order = queryo.setParameter("usernam", user).getSingleResult();
        } catch (NoResultException e) {
            order=null;
        }
         try {
            query = em.createQuery("SELECT u FROM Users  u WHERE u.mail LIKE :name", Users.class);
            us = query.setParameter("name", user).getSingleResult();

        } catch (NoResultException e) {
            return;
        }
         if(order==null){
             order = new Orders(us);
             em.persist(order);
         }        
        us.getOrders().add(order);
        for (LibraryItem libraryItem : cart) {
            libraryItem.getOrders().add(order);
        }
        for (int i = 0; i < cart.size(); i++) {
            order.getLibItem().add(cart.get(i));
        }
        checkAvaibility(cart);
        cart = new ArrayList<>();

    }

    private void checkAvaibility(List<LibraryItem> list) {
        Query query;
        long count;
        TypedQuery<LibraryItem> quer;
        LibraryItem results;

        for (LibraryItem libraryItem : list) {
            try {
                query = em.createQuery("SELECT COUNT(o) FROM Orders o INNER JOIN o.libItem l WHERE l.bookISBN.ISBN LIKE :name AND o.openOrder = TRUE");
                count = (long) query.setParameter("name", libraryItem.getBookISBN().getISBN()).getSingleResult();
            } catch (NoResultException e) {
                count = 0;
            }
            System.out.println(count);
            if (count >= libraryItem.getCount()) {
                quer = em.createQuery("SELECT l FROM LibraryItem  l WHERE l.bookISBN.ISBN LIKE :name", LibraryItem.class);
                results = quer.setParameter("name", libraryItem.getBookISBN().getISBN()).getSingleResult();
                results.setAvailability(Boolean.FALSE);
            }
        }
    }

    @Override
    public String getPassword(String usermail) {
        Users s;
        try {
            TypedQuery<Users> query = em.createQuery("SELECT u FROM Users u WHERE u.mail = :param", Users.class);
            s = query.setParameter("param", usermail).getSingleResult();
            return s.getPass();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public List<LibraryItem> getMyCart() {
        return cart;
    }

    @Override
    public List<Orders> getAllMyOrders(String user) {
        TypedQuery<Orders> query = em.createQuery("SELECT o FROM Orders o INNER JOIN o.user u WHERE u.mail LIKE :name AND o.openOrder = TRUE", Orders.class);
        List<Orders> results = query.setParameter("name", user).getResultList();
        return results;
    }
    
    @Override
    public List<WaitingListItem> getAllMyWishlists(String user) {
        TypedQuery<WaitingListItem> query = em.createQuery("SELECT w FROM WaitingListItem w INNER JOIN w.userWL u WHERE u.mail LIKE :name", WaitingListItem.class);
        List<WaitingListItem> results = query.setParameter("name", user).getResultList();
        return results;
    }

    @Override
    public String getBooksFromOrder(String id) {
        Long idl = Long.parseLong(id);
        String s="";
         TypedQuery<LibraryItem> query=em.createQuery("SELECT l FROM LibraryItem  l INNER JOIN l.orders o WHERE o.id = :id", LibraryItem.class);
        List<LibraryItem> results = query.setParameter("id", idl).getResultList();
        for (LibraryItem result : results) {
         s=s+ " , " + result.getBookISBN().getName()+"("+result.getBookISBN().getISBN()+")";
        }
        return s;
    }
  
    

}
