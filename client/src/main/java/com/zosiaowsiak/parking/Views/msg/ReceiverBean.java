package com.zosiaowsiak.parking.Views.msg;

import com.zosiaowsiak.parking.Contracts.DatabaseControllerInterface;
import com.zosiaowsiak.parking.Contracts.MessageSenderInterface;
import com.zosiaowsiak.parking.Models.Employee;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.inject.Inject;
import javax.jms.JMSConsumer;
import javax.jms.JMSContext;
import javax.jms.JMSRuntimeException;
import javax.jms.Queue;
import java.security.Principal;

@ManagedBean
@RequestScoped
public class ReceiverBean {

    @Inject
    private JMSContext context;

    @Resource(mappedName = "java:/jboss/exported/jms/queue/SOA_test")
    private Queue queue;

    public ReceiverBean() {
    }

    public void next(AjaxBehaviorEvent ajaxBehaviorEvent) {
        System.out.println("Próbuje odświeżyc");
    }

    public void getMessage() {
        System.out.println("w receiverze jestem");
        try {
            JMSConsumer receiver = context.createConsumer(queue);
            String text = receiver.receiveBody(String.class, 1000);

            if (text != null) {

                FacesMessage facesMessage =
                        new FacesMessage("Reading message: " + text);
                FacesContext.getCurrentInstance().addMessage(null, facesMessage);

            } else {
                FacesMessage facesMessage =
                        new FacesMessage("No new alerts for you.");
                FacesContext.getCurrentInstance().addMessage(null, facesMessage);
            }
        } catch (JMSRuntimeException t) {

            System.out.println(t.toString());
        }
    }

}
