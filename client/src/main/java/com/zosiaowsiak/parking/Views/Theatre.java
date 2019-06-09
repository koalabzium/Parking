package com.zosiaowsiak.parking.Views;

import com.zosiaowsiak.parking.Contracts.SeatAvailabilityServiceInterface;
import com.zosiaowsiak.parking.Contracts.SeatsServiceInterface;
import com.zosiaowsiak.parking.Contracts.TicketsServiceInterface;
import com.zosiaowsiak.parking.Exceptions.NotEnoughFundsException;
import com.zosiaowsiak.parking.Exceptions.SeatDoesNotExistException;
import com.zosiaowsiak.parking.Exceptions.SeatUnavailableException;
import com.zosiaowsiak.parking.Models.Seat;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named("Theatre")
@SessionScoped
public class Theatre implements Serializable {
//    @EJB(lookup = "java:global/server/TicketsService")
//    private TicketsServiceInterface ticketsService;
//
//    @EJB(lookup = "java:global/server/SeatAvailabilityService")
//    private SeatAvailabilityServiceInterface seatAvailabilityService;
//
//    @EJB(lookup = "java:global/server/SeatsService!SeatsServiceInterface")
//    private SeatsServiceInterface seatsService;
//
//    private String error;
//
//    public List<Seat> getSeats() {
//        return seatsService.getSeatList();
//    }
//
//    public boolean isSeatAvailable(int number) throws SeatDoesNotExistException {
//        return seatAvailabilityService.isAvailable(number);
//    }
//
//    public void buy(int number) {
//        try {
//            ticketsService.buyTicket(number);
//            error = null;
//        } catch (NotEnoughFundsException | SeatDoesNotExistException | SeatUnavailableException e) {
//            error = e.getMessage();
//        }
//    }
//
//    public String getError() {
//        return error;
//    }
//
//    public void setError(String error) {
//        this.error = error;
//    }
//
//    public int getWallet() {
//        return ticketsService.getWallet();
//    }
}
