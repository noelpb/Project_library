/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timersandinterceptors;

import beans.StaffSesBean;
import entities.Users;
import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.mail.MessagingException;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.mail.Authenticator;

/**
 *
 * @author Nudista
 */
@Singleton
public class Orderchecking implements OrdercheckingLocal {

    @EJB
    StaffSesBean staffSesBean;
    
    @PersistenceContext(unitName = "lPU")
    private EntityManager em;
    private Date today;

    @Schedule(hour = "12", minute = "01")
    @Override
    public void myTimer() {
        today = new Date();
        System.out.println("Timer event: " + today);
        System.out.println(checkOrder(today));
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @Override
    public String checkOrder(Date today) {
        TypedQuery<Users> query;
        List<Users> results;
        try {
            query = em.createQuery("SELECT u FROM Users u INNER JOIN u.orders o  WHERE o.endDate = :today", Users.class);
            results = query.setParameter("today", today).getResultList();
        } catch (NoResultException e) {
            results = null;
        }
        for (Users result : results) {
            try {
                staffSesBean.sendMail(result.getMail(), "Today is the last day of your order");
            } catch (MessagingException ex) {
                Logger.getLogger(Orderchecking.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return results.size() + " e-mails was sent today";
    }

    
}
