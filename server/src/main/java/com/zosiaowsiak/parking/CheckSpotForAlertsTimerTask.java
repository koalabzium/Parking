package com.zosiaowsiak.parking;



import com.zosiaowsiak.parking.Contracts.AlertManager;
import com.zosiaowsiak.parking.Database.ParkingLotDAO;
import com.zosiaowsiak.parking.Models.ParkingLot;

import java.util.TimerTask;

public class CheckSpotForAlertsTimerTask extends TimerTask {
    private int spotId;
    private AlertManager alertManager;

    public CheckSpotForAlertsTimerTask(int spotId, AlertManager alertManager) {
        this.spotId = spotId;
        this.alertManager = alertManager;
    }

    public void run() {
        System.out.println("Checking parking spot "+spotId);
        ParkingLotDAO parkingLotDAO = new ParkingLotDAO();
        ParkingLot spot = parkingLotDAO.getLotById(spotId);
        if (isAlertValid(spot)) {
            alertManager.alert(spot);
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
