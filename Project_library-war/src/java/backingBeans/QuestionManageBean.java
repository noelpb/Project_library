/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backingBeans;

import beans.StaffSesBeanLocal;
import entities.QuestionItem;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.enterprise.context.RequestScoped;
import javax.mail.MessagingException;

/**
 *
 * @author sam
 */
@Named(value = "questionManageBean")
@RequestScoped
public class QuestionManageBean {

    @EJB
    private StaffSesBeanLocal staffSesBean;

    private String response;
    private QuestionItem curr_question;

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }
    /**
     * Creates a new instance of QuestionManageBean
     */
    
    public QuestionManageBean() {
    }
    
    public QuestionItem getNextQuestion(){
        curr_question = staffSesBean.getOneQuestion();
        return curr_question;
    }
    
    public void sendReply() throws MessagingException{
        System.out.println("in Send Reply");
        System.out.println(staffSesBean.getOneQuestion());
        System.out.println(response);
        staffSesBean.answerQuestion(staffSesBean.getOneQuestion(), response);
    }
    
}
