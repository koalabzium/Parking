package com.zosiaowsiak.parking.Contracts;

import com.zosiaowsiak.parking.Exceptions.SeatDoesNotExistException;
import com.zosiaowsiak.parking.Exceptions.SeatUnavailableException;
import com.zosiaowsiak.parking.Models.Seat;

public interface LocalSeatsServiceInterface {
    Seat getSeatByNumber(int number) throws SeatDoesNotExistException;

    int getSeatPrice(int number) throws SeatDoesNotExistException;

    void buyTicket(int number) throws SeatUnavailableException, SeatDoesNotExistException;
}
