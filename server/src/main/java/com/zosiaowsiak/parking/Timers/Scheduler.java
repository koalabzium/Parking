package com.zosiaowsiak.parking.Timers;

import com.zosiaowsiak.parking.Contracts.SchedulerInterface;
import com.zosiaowsiak.parking.JMS.MessageSender;
import com.zosiaowsiak.parking.Models.Ticket;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Singleton;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;

@Remote(SchedulerInterface.class)
@Singleton
public class Scheduler implements SchedulerInterface {

    public void scheduleCheckingTicket(Ticket ticket) {
        System.out.println("SCHEDULING TICKET CHECK...");
        Timer timer = new Timer();
        timer.schedule(new ParkingTimerTask(ticket.getLotId()), ticket.getEndTime());
    }

    public void scheduleCheckingParkingLot(int lotId)  {
        System.out.println("SCHEDULING LOT CHECK...");

        Timer timer = new Timer();

        Calendar date = Calendar.getInstance();
        long timeInMillis = date.getTimeInMillis();
        Date afterAddingFiveMins = new Date(timeInMillis + (1 * 60 * 1000));

        //TODO użyj long delay
        timer.schedule(new ParkingTimerTask(lotId), afterAddingFiveMins);
    }

}