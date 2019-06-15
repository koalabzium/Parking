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
        if (ValidateNotification.isAlertValid(spot)) {
            alertManager.alert(spot);
        }
    }
}
