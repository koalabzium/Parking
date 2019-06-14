package com.zosiaowsiak.parking.Timers;

import com.zosiaowsiak.parking.Database.ParkingLotDAO;
import com.zosiaowsiak.parking.JMS.MessageSender;
import com.zosiaowsiak.parking.Models.ParkingLot;

import java.util.Calendar;
import java.util.Date;
import java.util.TimerTask;

public class AlertValidator  {

    private ParkingLotDAO parkingLotDAO = new ParkingLotDAO();


    public boolean shouldAlert(ParkingLot lot) {

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
