package com.zosiaowsiak.parking.Soap;


import com.zosiaowsiak.parking.Database.ParkingLotDAO;
import com.zosiaowsiak.parking.Models.ParkingLot;

import javax.ejb.Remote;
import javax.ejb.Singleton;
import javax.ejb.Stateless;
import javax.jws.WebService;

@Stateless
@Remote(SoapService.class)
//@WebService(endpointInterface = "com.zosiaowsiak.parking.Soap.SoapService")
public class SoapServiceImpl implements SoapService{

    @Override
    public void freeLot(Integer lotId) {
        ParkingLotDAO parkingLotDAO = new ParkingLotDAO();
        parkingLotDAO.setLotAsFree(lotId);
    }

    @Override
    public void takeLot(Integer lotId) {
        ParkingLotDAO parkingLotDAO = new ParkingLotDAO();
        parkingLotDAO.serLotAsTaken(lotId);
    }
}
