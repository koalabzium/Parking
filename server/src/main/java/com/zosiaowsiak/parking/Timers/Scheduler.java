package com.zosiaowsiak.parking.Timers;



import com.zosiaowsiak.parking.Contracts.SchedulerInterface;
import com.zosiaowsiak.parking.JMS.AlertSender;
import com.zosiaowsiak.parking.Models.ParkingLot;
import com.zosiaowsiak.parking.Models.Ticket;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Singleton;
import java.util.Calendar;
import java.util.Date;


@Remote(SchedulerInterface.class)
@Singleton
public class Scheduler implements SchedulerInterface {

    @EJB
    AlertSender alertSender;

    public void scheduleTicketCheck(Ticket ticket) {
        java.util.Timer timer = new java.util.Timer();
        timer.schedule(new Timer(ticket.getLotId(),this), ticket.getEndTime());
    }

    public void scheduleSpotCheck(int spotId)  {
        System.out.println("----------------------------------------------------------");
        System.out.println("USTAWIAM TIMER...");
        java.util.Timer timer = new java.util.Timer();

        long ONE_MINUTE_IN_MILLIS=60000;
        Calendar date = Calendar.getInstance();
        long timeInMillis = date.getTimeInMillis();
        Date afterAddingFiveMins = new Date(timeInMillis + (1 * ONE_MINUTE_IN_MILLIS));

        timer.schedule(new Timer(spotId, this), afterAddingFiveMins);
    }

    public void alert(ParkingLot spot) {
        System.out.println("Detected unpaid parking spot!");
        System.out.println("alertSender is null: " + (alertSender == null));

        alertSender.sendMessage("" + spot.getId());
    }
}
