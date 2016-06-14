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
import entities.QuestionItem;
import entities.Users;
import java.util.Date;
import java.util.Properties;
import javax.ejb.Stateful;
import javax.ejb.Stateless;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author Nudista
 */
@Stateful
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
        try {
            query = em.createQuery("SELECT o FROM Orders o INNER JOIN o.user u WHERE u.mail = :param AND o.startDate = :date", Orders.class);
            query.setParameter("date", ost);
            o = query.setParameter("param", user).getSingleResult();
        } catch (NoResultException | NonUniqueResultException ex) {
            return false;
        }
        o.setOpenOrder(false);

        for (LibraryItem li : o.getLibItem()) {
            li.setAvailability(true);
        }

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

    @Override
    public boolean checkDirector(String usermail) {
        Users s;
        try {
            TypedQuery<Users> query = em.createQuery("SELECT u FROM Users u WHERE u.mail = :param AND u.masterAdmin = TRUE", Users.class);
            s = query.setParameter("param", usermail).getSingleResult();
         } catch (NoResultException e) {
            return false;
        }
        if(s.isMasterAdmin()){
            return true;
        }else{
            return false;
        }
    }
    
    @Override
    public QuestionItem getOneQuestion(){
        QuestionItem q;
        try {
            TypedQuery<QuestionItem> query = em.createQuery("SELECT q FROM QuestionItem q", QuestionItem.class);
            query.setFirstResult(0);
            query.setMaxResults(1);
            q = query.getSingleResult();
            return q;
         } catch (NoResultException e) {
            return null;
        }
    }
    
    @Override
    public void answerQuestion(QuestionItem q, String answer) throws MessagingException{
        sendMail(q.getMail(), answer);
        System.out.println(q.getMail());
        TypedQuery<QuestionItem> query;
        QuestionItem i = null;
        boolean res;
        try {
            query = em.createQuery("SELECT l FROM QuestionItem  l WHERE l.id = :name", QuestionItem.class);
            Long id = q.getId();
            i = query.setParameter("name", id).getSingleResult();
            em.remove(i);
            res = true;
        } catch (NoResultException e) {
            res = false;
        }
      
        
    }
    @Override
    public void sendMail(String mail, String text) throws MessagingException {
        String d_email = "matousmejem@seznam.cz",
                d_uname = "matousmejem@seznam.cz",
                d_password = "Palec5529",
                d_host = "smtp.seznam.cz",
                d_port = "465",
                m_to = mail,
                m_subject = "Group T library ",
                m_text = text;
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
