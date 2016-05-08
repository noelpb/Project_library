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
import entities.Users;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
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
    public void addLibItem(int count, Genre g, String author, int pages, String isbn, String bookname) {
        a = new AuthorItem(author);
        b = new BookItem(bookname, pages, isbn, g);
        b.getWriter().add(a);
        a.getBook().add(b);
        l = new LibraryItem(b, count);
        em.persist(l);
        em.persist(b);
        em.persist(a);
    }

    @Override
    public void addUser(String name, String surname, String mail, String pass) {
        u = new Users(name, surname, mail, pass);
        em.persist(u);
    }

    @Override
    public void closeOrder(String user, Date orderDate) {
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
}
