    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import entities.LibraryItem;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Noel
 */
@Stateless
@LocalBean
 
public class getBooks {
    @PersistenceContext(unitName = "lPU")
    private EntityManager em;
    public String getBooks() {
        
      //  String str[]={"One","Two","Three"};
      //  return str[(int)(Math.random()*str.length)];
       List<LibraryItem> results = em.createNamedQuery("selectAll", LibraryItem.class).getResultList();
        return results.toString();
    }
    public String getBookById(int id) {
        
      //  String str[]={"One","Two","Three"};
      //  return str[(int)(Math.random()*str.length)];
        System.out.println(id);
       List<LibraryItem> results = em.createNamedQuery("selectAll", LibraryItem.class).getResultList();
        return results.get(id).toString();
    }
    
    
}
