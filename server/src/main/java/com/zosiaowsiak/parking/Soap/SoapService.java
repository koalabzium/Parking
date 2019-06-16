package com.zosiaowsiak.parking.Soap;

import com.zosiaowsiak.parking.Contracts.SchedulerInterface;
import com.zosiaowsiak.parking.Database.ParkingLotDAO;
import com.zosiaowsiak.parking.Database.TicketDAO;
import com.zosiaowsiak.parking.Models.Ticket;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

@Stateless
@WebService
public class SoapService {


    @EJB(lookup = "java:global/server/Scheduler")
    SchedulerInterface schedulerInterface;

    @WebMethod(action = "Arrive")
    @WebResult(name = "greet")
    public void Arrive(@WebParam(name="answer") Integer lot) {
        System.out.println("JESTEM W SOAPSERVICE");
        ParkingLotDAO parkingLotDAO = new ParkingLotDAO();
        parkingLotDAO.setLotAsTaken(lot);
        schedulerInterface.scheduleSpotCheck(lot);
    }

    @WebMethod(action = "Leave")
    @WebResult(name = "greet")
    public void Leave(@WebParam(name="answer") Integer lot) {
        ParkingLotDAO parkingLotDAO = new ParkingLotDAO();

        parkingLotDAO.setLotAsFree(lot);
    }

    @WebMethod(action = "Ticket")
    @WebResult(name = "greet")
    public void Ticket(@WebParam(name="answer") Integer ticketId) {
        TicketDAO ticketDAO = new TicketDAO();
        Ticket ticket = ticketDAO.getTicketById(ticketId);
        System.out.println(".................................................................");
        System.out.println("W action Ticket");
        System.out.println(".................................................................");
        schedulerInterface.scheduleTicketCheck(ticket);
    }

}
