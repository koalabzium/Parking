package com.zosiaowsiak.parking.Contracts;

import com.zosiaowsiak.parking.Models.ParkingLot;
import com.zosiaowsiak.parking.Models.Ticket;

public interface SchedulerInterface {
    void scheduleCheckingTicket(Ticket ticket);
    void scheduleCheckingParkingLot(int lotId);

    void alert(ParkingLot lot);
}
