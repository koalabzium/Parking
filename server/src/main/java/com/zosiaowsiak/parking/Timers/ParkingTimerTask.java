package com.zosiaowsiak.parking.Timers;

import com.zosiaowsiak.parking.Database.ParkingLotDAO;
import com.zosiaowsiak.parking.JMS.MessageSender;
import com.zosiaowsiak.parking.Models.ParkingLot;

import java.util.TimerTask;

public class ParkingTimerTask extends TimerTask {

    private Integer lotId;
    private MessageSender messageSender = new MessageSender();
    private ParkingLotDAO parkingLotDAO = new ParkingLotDAO();
    private AlertValidator alertValidator = new AlertValidator();

    public ParkingTimerTask(Integer lotId){

        this.lotId = lotId;

    }

    @Override
    public void run() {

        System.out.println("TIMER RUN Checking parking spot "+lotId);
        ParkingLot lot = parkingLotDAO.getLotById(lotId);
        if (alertValidator.shouldAlert(lot)) {
            System.out.println("ALERT!!! - PARKING  LOT: " + lotId);
            messageSender.sendMessage("CHECK PARKING LOT NR: " + lotId, lot.getArea());
        }

    }
}
