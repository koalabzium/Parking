package com.zosiaowsiak.parking.JMS;

import com.zosiaowsiak.parking.Contracts.MessageSenderInterface;

import javax.annotation.Resource;
import javax.ejb.Remote;
import javax.ejb.Singleton;
import javax.ejb.Stateless;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.jms.*;

@Stateless
@Remote(MessageSenderInterface.class)
public class MessageSender
        implements MessageSenderInterface
{

    @Resource(mappedName = "java:/jboss/exported/jms/queue/SOA_test")
    private Queue myQueue;

    @Inject
    @JMSConnectionFactory("java:jboss/DefaultJMSConnectionFactory")
    private JMSContext context;

    public void sendMessage(String messageText, Integer area){
        try {
            String text = "Message: \"" + messageText + "\" For area: " + area;
            System.out.println("SENDING MESSAGE...");
            context.createProducer().send(myQueue, text);
//
            System.out.println("MESSAGE SENT: " + text);
//            FacesMessage facesMessage =
//                    new FacesMessage("Sent message: " + text);
//            FacesContext.getCurrentInstance().addMessage(null, facesMessage);
        } catch (JMSRuntimeException t) {
            System.out.println(t.toString());
        }
    }


}
