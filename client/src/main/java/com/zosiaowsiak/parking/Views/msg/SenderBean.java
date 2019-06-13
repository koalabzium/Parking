package com.zosiaowsiak.parking.Views.msg;

import javax.annotation.Resource;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.jms.JMSConnectionFactory;
import javax.jms.JMSContext;
import javax.jms.JMSRuntimeException;
import javax.jms.Queue;

@ManagedBean
@RequestScoped
public class SenderBean {

    private String messageText;

    @Resource(mappedName = "java:/jboss/exported/jms/queue/SOA_test")
    private Queue myQueue;

    @Inject
    @JMSConnectionFactory("java:jboss/DefaultJMSConnectionFactory")
    private JMSContext context;




    public SenderBean() {
    }

    public void sendJMSMessageToMyQueue() {
        try {
            String text = "Message from producer: " + messageText;
            context.createProducer().send(myQueue, text);

            FacesMessage facesMessage =
                    new FacesMessage("Sent message: " + text);
            FacesContext.getCurrentInstance().addMessage(null, facesMessage);
        } catch (JMSRuntimeException t) {
            System.out.println(t.toString());
        }
    }


    public String getMessageText() {
        return messageText;
    }

    public void setMessageText(String messageText) {
        this.messageText = messageText;
    }
}
