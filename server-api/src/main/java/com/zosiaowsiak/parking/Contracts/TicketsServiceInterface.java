package com.zosiaowsiak.parking.Contracts;

import com.zosiaowsiak.parking.Exceptions.NotEnoughFundsException;
import com.zosiaowsiak.parking.Exceptions.SeatDoesNotExistException;
import com.zosiaowsiak.parking.Exceptions.SeatUnavailableException;

public interface TicketsServiceInterface {
    void buyTicket(int number) throws SeatDoesNotExistException, NotEnoughFundsException, SeatUnavailableException;

    int getWallet();
}
