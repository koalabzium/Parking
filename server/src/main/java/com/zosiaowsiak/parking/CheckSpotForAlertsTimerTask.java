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
        if (!spot.getIsoccupied())
            return false;

        ParkingLotDAO parkingLotDAO = new ParkingLotDAO();
        if (parkingLotDAO.hasValidTicket(spot))
            return false;

//        long ONE_MINUTE_IN_MILLIS = 60000;
//        Calendar date = Calendar.getInstance();
//        long timeInMillis = date.getTimeInMillis();
//        Date afterSubtractingFiveMins = new Date(timeInMillis - (5 * ONE_MINUTE_IN_MILLIS-1));
//
//        if (spot.getArrivalTime().before(afterSubtractingFiveMins))
//            return true;

        return true;
    }
}
