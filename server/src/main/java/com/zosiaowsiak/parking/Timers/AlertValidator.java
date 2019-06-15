package com.zosiaowsiak.parking.Timers;

import com.zosiaowsiak.parking.Contracts.MessageSenderInterface;
import com.zosiaowsiak.parking.Database.ParkingLotDAO;
import com.zosiaowsiak.parking.JMS.MessageSender;
import com.zosiaowsiak.parking.Models.ParkingLot;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;
import javax.jms.*;
import java.util.Calendar;
import java.util.Date;
import java.util.TimerTask;


@ManagedBean
@SessionScoped
public class AlertValidator extends TimerTask {

    private Integer lotId;
    private Scheduler scheduler;
    private ParkingLotDAO parkingLotDAO = new ParkingLotDAO();


    @EJB(lookup = "java:global/server/MessageSender")
    private MessageSenderInterface messageSender;


    @Resource(mappedName = "java:/jboss/exported/jms/queue/SOA_test")
    private Queue myQueue;

    @Inject
    @JMSConnectionFactory("java:jboss/DefaultJMSConnectionFactory")
    private JMSContext context;

    public  AlertValidator(){}

    public AlertValidator(Integer lotId, Scheduler scheduler) {
        this.lotId = lotId;
        this.scheduler = scheduler;
    }

    @Override
    public void run() {

        System.out.println("TIMER RUN Checking parking spot "+lotId);

        ParkingLot lot = parkingLotDAO.getLotById(lotId);
        if (shouldAlert(lot)) {
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            try {
                Queue queue = session.createQueue("customerQueue");
            } catch (JMSException e) {
                e.printStackTrace();
            }
            System.out.println("ALERT!!! - PARKING  LOT: " + lotId);
            messageSender.sendMessage("alert", 2);
        }

    }

    private boolean shouldAlert(ParkingLot lot) {

        if(!lot.getIsoccupied()) {
            return false;
        }

        return !parkingLotDAO.hasValidTicket(lot);
    }

}
