/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import entities.QuestionItem;
import entities.LibraryItem;
import javax.annotation.Resource;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.EJB;
import javax.ejb.MessageDriven;
import javax.ejb.MessageDrivenContext;
import javax.jms.JMSDestinationDefinition;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author sam
 */

@MessageDriven(mappedName="jms/bookQueue",
        activationConfig = {
    @ActivationConfigProperty(propertyName = "acknowledgeMode", propertyValue = "Auto-acknowledge"),
    @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue")
})
public class BookMessage implements MessageListener {
    @EJB
    private StaffSesBeanLocal staff;
    @Resource
    private MessageDrivenContext mdc;

    @PersistenceContext(unitName = "lPU")
    private EntityManager em;


    public BookMessage() {
    }
    
    
    @Override
    public void onMessage(Message message) {
        ObjectMessage objectMessage;
        try{
            objectMessage = (ObjectMessage) message;
            QuestionItem li = (QuestionItem) objectMessage.getObject();
            em.persist(li);
        }catch (JMSException e){
            mdc.setRollbackOnly();
        }
    }

    public void save(Object object) {
        em.persist(object);
    }
    
}
