package com.zosiaowsiak.parking.Soap;

import com.zosiaowsiak.parking.Database.DatabaseController;

import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.crypto.Data;

@Stateless
@WebService
public class SoapService {

    @WebMethod(action = "Arrive")
    @WebResult(name = "greet")
    public void Arrive(@WebParam(name="answer") Integer lot){
        DatabaseController databaseController = new DatabaseController();
        databaseController.setLotAsTaken(lot);
    }

    @WebMethod(action = "Leave")
    @WebResult(name = "greet")
    public void Leave(@WebParam(name="answer") Integer lot){
        DatabaseController databaseController = new DatabaseController();
        databaseController.setLotAsFree(lot);
    }
}
