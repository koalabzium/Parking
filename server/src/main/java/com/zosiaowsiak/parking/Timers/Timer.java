package com.zosiaowsiak.parking.Timers;



import com.zosiaowsiak.parking.Contracts.SchedulerInterface;
import com.zosiaowsiak.parking.Database.ParkingLotDAO;
import com.zosiaowsiak.parking.Models.ParkingLot;

import java.util.TimerTask;

public class Timer extends TimerTask {
    private int spotId;
    private SchedulerInterface schedulerInterface;

    public Timer(int spotId, SchedulerInterface schedulerInterface) {
        this.spotId = spotId;
        this.schedulerInterface = schedulerInterface;
    }

    public void run() {
        System.out.println("Checking parking spot "+spotId);
        ParkingLotDAO parkingLotDAO = new ParkingLotDAO();
        ParkingLot spot = parkingLotDAO.getLotById(spotId);
        if (isAlertValid(spot)) {
            schedulerInterface.alert(spot);
        }
    }

    public static boolean isAlertValid(ParkingLot spot) {
        ParkingLotDAO parkingLotDAO = new ParkingLotDAO();
        System.out.println("Is alert valid for spot: " + spot.getId());
        System.out.println("The spot is occupied: " + spot.getIsoccupied());
        System.out.println("The spot has valid ticket: " + parkingLotDAO.hasValidTicket(spot));
        if (!spot.getIsoccupied()) {
            return false;
        }

        if (parkingLotDAO.hasValidTicket(spot))
            return false;

        System.out.println("isAlertValid - po sprawdzeniu ");
        return true;
    }
}
