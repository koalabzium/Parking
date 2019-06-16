package com.zosiaowsiak.parking.Views.msg;

import com.zosiaowsiak.parking.Contracts.MessageSenderInterface;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import java.io.Serializable;

@ManagedBean
@RequestScoped
public class SenderBean implements Serializable {

    private String messageText;


//    @Resource(mappedName = "java:/jboss/exported/jms/queue/SOA_test")
//    private Queue myQueue;
//
//    @Inject
//    @JMSConnectionFactory("java:jboss/DefaultJMSConnectionFactory")
//    private JMSContext context;

    @EJB(lookup = "java:global/server/MessageSender")
    private MessageSenderInterface messageSender;




    public SenderBean() {
    }


    public void sendMessage() {
        messageSender.sendMessage(messageText, 2);
    }


    public String getMessageText() {
        return messageText;
    }

    public void setMessageText(String messageText) {
        this.messageText = messageText;
    }
}
