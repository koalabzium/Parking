package com.zosiaowsiak.parking.Contracts;

import com.zosiaowsiak.parking.Exceptions.SeatDoesNotExistException;

public interface SeatAvailabilityServiceInterface {
    boolean isAvailable(int number) throws SeatDoesNotExistException;
}
