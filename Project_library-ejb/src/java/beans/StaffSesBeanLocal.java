/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import entities.Genre;
import entities.QuestionItem;
import java.util.Date;
import javax.ejb.Local;
import javax.mail.MessagingException;

/**
 *
 * @author Nudista
 */
@Local
public interface StaffSesBeanLocal {

    public boolean addLibItem(int count, Genre g, String author, int pages, String isbn, String bookname);

    public boolean addUser(String name, String surname, String mail, String pass);

    boolean closeOrder(String user, String orderDate);

    String getPassword(String usermail);

    boolean deleteUser(String mail);

    boolean checkDirector(String usermail);

    public QuestionItem getOneQuestion();

    public void answerQuestion(QuestionItem q, String answer) throws MessagingException;

    public void sendMail(String mail, String text) throws MessagingException;
}
