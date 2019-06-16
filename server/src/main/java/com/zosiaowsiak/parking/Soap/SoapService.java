package com.zosiaowsiak.parking.Soap;

import com.zosiaowsiak.parking.Contracts.AlertManager;
import com.zosiaowsiak.parking.Database.ParkingLotDAO;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

@Stateless
@WebService
public class SoapService {


    @EJB(lookup = "java:global/server/AlertManagerBean")
    AlertManager alertManager;

    @WebMethod(action = "Arrive")
    @WebResult(name = "greet")
    public void Arrive(@WebParam(name="answer") Integer lot) {
        System.out.println("JESTEM W SOAPSERVICE");
        ParkingLotDAO parkingLotDAO = new ParkingLotDAO();
        parkingLotDAO.setLotAsTaken(lot);
        alertManager.scheduleSpotCheck(lot);
    }

    @WebMethod(action = "Leave")
    @WebResult(name = "greet")
    public void Leave(@WebParam(name="answer") Integer lot) {
        ParkingLotDAO parkingLotDAO = new ParkingLotDAO();

        parkingLotDAO.setLotAsFree(lot);
    }



//    @WebMethod(action = "Arrive")
//    @WebResult(name = "greet")
//    public void Arrive(@WebParam(name="answer") Integer lot){
//        Scheduler scheduler = new Scheduler();
//        scheduler.scheduleCheckingParkingLot(lot);
//        DatabaseController databaseController = new DatabaseController();
//        databaseController.setLotAsTaken(lot);
//    }
//
//    @WebMethod(action = "Leave")
//    @WebResult(name = "greet")
//    public void Leave(@WebParam(name="answer") Integer lot){
//        DatabaseController databaseController = new DatabaseController();
//        databaseController.setLotAsFree(lot);
//    }
}
