package com.zosiaowsiak.parking;



import com.zosiaowsiak.parking.Contracts.AlertManager;
import com.zosiaowsiak.parking.JMS.AlertSender;
import com.zosiaowsiak.parking.Models.ParkingLot;
import com.zosiaowsiak.parking.Models.Ticket;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Singleton;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;


@Remote(AlertManager.class)
@Singleton
public class AlertManagerBean implements AlertManager {

    @EJB
    AlertSender alertSender;

    public void scheduleTicketCheck(Ticket ticket) {
        Timer timer = new Timer();
//        timer.schedule(new CheckSpotForAlertsTimerTask(ticket.getLotId(),this), ticket.getEndTime());
    }

    public void scheduleSpotCheck(int spotId)  {
        System.out.println("----------------------------------------------------------");
        System.out.println("USTAWIAM TIMER...");
        Timer timer = new Timer();

        long ONE_MINUTE_IN_MILLIS=60000;
        Calendar date = Calendar.getInstance();
        long timeInMillis = date.getTimeInMillis();
        Date afterAddingFiveMins = new Date(timeInMillis + (1 * ONE_MINUTE_IN_MILLIS));

        timer.schedule(new CheckSpotForAlertsTimerTask(spotId, this), afterAddingFiveMins);
    }

    public void alert(ParkingLot spot) {
        System.out.println("Detected unpaid parking spot!");

        alertSender.sendMessage("" + spot.getId());
    }
}
