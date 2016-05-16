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
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
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
                sendMail(result.getMail());
            } catch (MessagingException ex) {
                Logger.getLogger(Orderchecking.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return results.size() + " e-mails was sent today";
    }

    private void sendMail(String mail) throws MessagingException {
        String d_email = "matousmejem@seznam.cz",
                d_uname = "matousmejem@seznam.cz",
                d_password = "Palec5529",
                d_host = "smtp.seznam.cz",
                d_port = "465",
                m_to = mail,
                m_subject = "Group T library ",
                m_text = "Today is the last day of your order";
        Properties props = new Properties();
        props.put("mail.smtp.user", d_email);
        props.put("mail.smtp.host", d_host);
        props.put("mail.smtp.port", d_port);
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.debug", "true");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.socketFactory.port", d_port);
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.socketFactory.fallback", "false");

        Authenticator auth = new SMTPAuthenticator();
        Session session = Session.getInstance(props, auth);
        MimeMessage msg = new MimeMessage(session);
        try {
            msg.setSubject(m_subject);
            msg.setText(m_text);
            msg.setFrom(new InternetAddress(d_email));
            msg.addRecipient(Message.RecipientType.TO, new InternetAddress(m_to));
            Transport transport = session.getTransport("smtps");
            transport.connect(d_host, Integer.valueOf(d_port), d_uname, d_password);
            transport.sendMessage(msg, msg.getAllRecipients());
            transport.close();
        } catch (AddressException e) {
            e.printStackTrace();
            System.out.println("error address");
        } catch (MessagingException e) {
            e.printStackTrace();
            System.out.println("error Messaging");
        }
    }

    private class SMTPAuthenticator extends javax.mail.Authenticator {

        @Override
        public PasswordAuthentication getPasswordAuthentication() {
            String username = "matousmejem@seznam.cz";
            String password = "Palec5529";
            return new PasswordAuthentication(username, password);
        }
    }
}
