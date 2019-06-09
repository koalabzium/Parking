package com.zosiaowsiak.parking.Beans;

import com.zosiaowsiak.parking.Contracts.LocalSeatsServiceInterface;
import com.zosiaowsiak.parking.Contracts.TicketsServiceInterface;
import com.zosiaowsiak.parking.Exceptions.NotEnoughFundsException;
import com.zosiaowsiak.parking.Exceptions.SeatDoesNotExistException;
import com.zosiaowsiak.parking.Exceptions.SeatUnavailableException;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateful;

@Stateful
@Remote(TicketsServiceInterface.class)
public class TicketsService implements TicketsServiceInterface {
    @EJB
    private LocalSeatsServiceInterface seatsService;

    private int wallet = 1000;

    @Override
    public void buyTicket(int number) throws SeatDoesNotExistException, NotEnoughFundsException, SeatUnavailableException {
        int price = seatsService.getSeatPrice(number);

        if (price > wallet) {
            throw new NotEnoughFundsException(number, price, wallet);
        }

        seatsService.buyTicket(number);
        wallet -= price;
    }

    @Override
    public int getWallet() {
        return wallet;
    }
}
