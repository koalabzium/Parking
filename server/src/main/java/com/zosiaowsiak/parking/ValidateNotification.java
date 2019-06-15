package com.zosiaowsiak.parking;

import com.zosiaowsiak.parking.Database.ParkingLotDAO;
import com.zosiaowsiak.parking.Models.ParkingLot;
;

import java.util.Calendar;
import java.util.Date;

public class ValidateNotification {
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
