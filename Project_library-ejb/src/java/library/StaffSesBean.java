/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package library;

import entities.AuthorItem;
import entities.BookItem;
import entities.Genre;
import entities.LibraryItem;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.hibernate.validator.internal.util.Contracts;

/**
 *
 * @author Nudista
 */
@Stateless
public class StaffSesBean implements StaffSesBeanLocal {

    private AuthorItem a;
    private BookItem b;
    private LibraryItem l;
   @PersistenceContext(unitName = "lPU")
   private EntityManager em;

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @Override
    public boolean addLibItem(int count, Genre g, String author, int pages, String isbn, String bookname) {
        a = new AuthorItem(author);
        b = new BookItem(bookname, pages, isbn, g);
        b.getWriter().add(a);
        a.getBook().add(b);
        l = new LibraryItem(b, count);
        em.persist(l);
        em.persist(b);
        em.persist(a);
        return true;
    }

}
