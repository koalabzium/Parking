package com.zosiaowsiak.parking.Timers;

import com.zosiaowsiak.parking.Database.ParkingLotDAO;
import com.zosiaowsiak.parking.JMS.MessageSender;
import com.zosiaowsiak.parking.Models.ParkingLot;

import java.util.Calendar;
import java.util.Date;
import java.util.TimerTask;

public class AlertValidator extends TimerTask {

    private Integer lotId;
    private ParkingLotDAO parkingLotDAO = new ParkingLotDAO();
    private MessageSender messageSender = new MessageSender();

    public AlertValidator(Integer lotId){

        this.lotId = lotId;

    }

    @Override
    public void run() {

        System.out.println("TIMER RUN Checking parking spot "+lotId);

        ParkingLot lot = parkingLotDAO.getLotById(lotId);
        if (shouldAlert(lot)) {
            System.out.println("ALERT!!! - PARKING  LOT: " + lot);
            messageSender.sendMessage("CHECK PARKING LOT NR: " + lotId, lot.getArea());
        }

    }

    private boolean shouldAlert(ParkingLot lot) {

        if(!lot.getIsoccupied()) return false;

        if(parkingLotDAO.hasValidTicket(lot)) return false;

        return !lessThanTenMinutes(lot);
    }

    private boolean lessThanTenMinutes(ParkingLot lot) {

        Calendar date = Calendar.getInstance();
        long currTimeMillis = date.getTimeInMillis();
        Date maxArrivalTime = new Date(currTimeMillis - (10 * 60 * 1000-1));
        return !lot.getArrivalTime().before(maxArrivalTime);
    }
}
