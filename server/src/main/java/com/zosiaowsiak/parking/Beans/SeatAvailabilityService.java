package com.zosiaowsiak.parking.Beans;

import com.zosiaowsiak.parking.Contracts.LocalSeatsServiceInterface;
import com.zosiaowsiak.parking.Contracts.SeatAvailabilityServiceInterface;
import com.zosiaowsiak.parking.Exceptions.SeatDoesNotExistException;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;

@Stateless
@Remote(SeatAvailabilityServiceInterface.class)
public class SeatAvailabilityService implements SeatAvailabilityServiceInterface {
    @EJB
    private LocalSeatsServiceInterface seatsService;

    @Override
    public boolean isAvailable(int number) throws SeatDoesNotExistException {
        return seatsService.getSeatByNumber(number).isAvailable();
    }
}
