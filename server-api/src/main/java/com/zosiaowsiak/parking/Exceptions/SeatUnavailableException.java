package com.zosiaowsiak.parking.Exceptions;

import java.io.Serializable;

public class SeatUnavailableException extends Exception implements Serializable {
    public SeatUnavailableException(int number) {
        super(String.format("Seat #%d is unavailable.", number));
    }
}
