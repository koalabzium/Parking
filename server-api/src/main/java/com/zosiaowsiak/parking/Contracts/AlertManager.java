package com.zosiaowsiak.parking.Contracts;


import com.zosiaowsiak.parking.Models.ParkingLot;
import com.zosiaowsiak.parking.Models.Ticket;

import javax.ejb.Remote;

@Remote
public interface AlertManager {
    void alert(ParkingLot spot);
    void scheduleSpotCheck(int spotId);
    void scheduleTicketCheck(Ticket ticket);
}
