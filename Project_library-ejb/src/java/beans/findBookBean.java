/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import entities.Genre;
import entities.LibraryItem;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author Nudista
 */
@Stateless
public class findBookBean implements findBookBeanLocal {
 @PersistenceContext(unitName = "lPU")
    private EntityManager em;
 
 
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

    @Override
    public List<LibraryItem> findISBN(String ISBN) {
         TypedQuery<LibraryItem> query=em.createQuery("SELECT l FROM LibraryItem  l WHERE l.bookISBN.ISBN LIKE :name", LibraryItem.class);
        List<LibraryItem> results = query.setParameter("name", "%"+ISBN+"%").getResultList();
        return results;
    }
    
    @Override
    public List<LibraryItem> findName(String book) {
        TypedQuery<LibraryItem> query=em.createQuery("SELECT l FROM LibraryItem  l WHERE l.bookISBN.name LIKE :name", LibraryItem.class);
        List<LibraryItem> results = query.setParameter("name", "%"+book+"%").getResultList();
        return results;
    }
    
    @Override
    public List<LibraryItem> findAuthor(String author) {
         TypedQuery<LibraryItem> query=em.createQuery("SELECT l FROM LibraryItem l INNER JOIN l.bookISBN.writer w  WHERE w.name LIKE :name", LibraryItem.class);
        List<LibraryItem> results = query.setParameter("name", "%"+author+"%").getResultList();
        return results;
    }

    @Override
    public List<LibraryItem> findGenre(String genre) {
       TypedQuery<LibraryItem> query=em.createQuery("SELECT l FROM LibraryItem  l WHERE l.bookISBN.genre = :name", LibraryItem.class);
       Genre g = Genre.valueOf(genre);
        List<LibraryItem> results = query.setParameter("name", g).getResultList();
        return results;
    }

    @Override
    public List<LibraryItem> selectAll() {
        List<LibraryItem> results = em.createNamedQuery("selectAll", LibraryItem.class).getResultList();
        return results;
    }
    
  
    
    
}
