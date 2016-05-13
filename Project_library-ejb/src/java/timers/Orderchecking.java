/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timers;

import entities.Users;
import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;
import java.util.Properties;
import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.mail.MessagingException;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author Nudista
 */
@Singleton
public class Orderchecking implements OrdercheckingLocal {

    @PersistenceContext(unitName = "lPU")
    private EntityManager em;
    private Date today;

    @Schedule(hour = "13", minute = "09")
    @Override
    public void myTimer() {
        today = new Date();
        System.out.println("Timer event: " + today);
        checkOrder(today);
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
            sendMail(result.getMail());
        }

        return results.size() + "e-mails sends today";

    }

    private void sendMail(String mail) {
        // Recipient's email ID needs to be mentioned.
        String to = mail;

        // Sender's email ID needs to be mentioned
        String from = "matousmejem@seznam.cz";

        // Assuming you are sending email from localhost
        String host = "localhost";

        // Get system properties
        Properties properties = System.getProperties();

        // Setup mail server
        properties.setProperty("mail.smtp.host", host);

        // Get the default Session object.
        Session session = Session.getDefaultInstance(properties);

        try {
            // Create a default MimeMessage object.
            MimeMessage message = new MimeMessage(session);

            // Set From: header field of the header.
            message.setFrom(new InternetAddress(from));

            // Set To: header field of the header.
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

            // Set Subject: header field
            message.setSubject("Groep T library order");

            // Now set the actual message
            message.setText("Dear user, please give all the books back or we will kill you!");

            // Send message
            Transport.send(message);
            System.out.println("Sent message successfully....");
        } catch (MessagingException mex) {
            mex.printStackTrace();
        }
    }
}
