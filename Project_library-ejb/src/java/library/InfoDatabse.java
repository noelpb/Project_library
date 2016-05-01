/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package library;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Nudista
 */
@Stateless
public class InfoDatabse implements InfoDatabseLocal {

    private EntityManagerFactory emf;
    private EntityManager em;
    private int count;

    @Override
    public String getDatabaseInfo() {
        emf = Persistence.createEntityManagerFactory("chapter04PU");
        em = emf.createEntityManager();
        count = ((Number) em.createNamedQuery("countLibItems").getSingleResult()).intValue();
        em.close();
        emf.close();
        return count + "";
    }
    
    

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @Override
    public String getInfoUsers() {
        emf = Persistence.createEntityManagerFactory("chapter04PU");
        em = emf.createEntityManager();
        count = ((Number) em.createNamedQuery("countUsers").getSingleResult()).intValue();
        em.close();
        emf.close();
        return count + "";
    }

    @Override
    public String getInfoOrders() {
        emf = Persistence.createEntityManagerFactory("chapter04PU");
        em = emf.createEntityManager();
        count = ((Number) em.createNamedQuery("countOrders").getSingleResult()).intValue();
        em.close();
        emf.close();
        return count + "";
    }

    @Override
    public String getInfoBooks() {
        emf = Persistence.createEntityManagerFactory("chapter04PU");
        em = emf.createEntityManager();
        count = ((Number) em.createNamedQuery("countBooks").getSingleResult()).intValue();
        em.close();
        emf.close();
        return count + "";
    }

    @Override
    public String test() {
        return "test";
    }
}
