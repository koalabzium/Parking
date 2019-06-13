package com.zosiaowsiak.parking.Views.msg;

import javax.annotation.Resource;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.jms.JMSConsumer;
import javax.jms.JMSContext;
import javax.jms.JMSRuntimeException;
import javax.jms.Queue;

@ManagedBean
@RequestScoped
public class ReceiverBean {

    @Inject
    private JMSContext context;

    @Resource(mappedName = "java:/jboss/exported/jms/queue/SOA_test")
    private Queue queue;

    public ReceiverBean() {
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
                        new FacesMessage("No message received after 1 second");
                FacesContext.getCurrentInstance().addMessage(null, facesMessage);
            }
        } catch (JMSRuntimeException t) {

            System.out.println(t.toString());
        }
    }
}
