package com.zosiaowsiak.parking.Soap;


import com.zosiaowsiak.parking.AlertManagerBean;
import com.zosiaowsiak.parking.Contracts.AlertManager;
import com.zosiaowsiak.parking.Database.ParkingLotDAO;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebService;

@Stateless
@WebService
public class SpotService {

    @EJB(lookup = "java:global/server/AlertManagerBean"
//            "!Contracts.AlertManagerLocal"
    )
    AlertManager alertManager;
//
//    @EJB
//    AlertManagerBean alertManager = new AlertManagerBean();

    @WebMethod(operationName = "updateSpotOccupied")
    public void updateSpotOccupied(int spot_id) {
        ParkingLotDAO parkingLotDAO = new ParkingLotDAO();
        parkingLotDAO.setLotAsTaken(spot_id);
        alertManager.scheduleSpotCheck(spot_id);
    }

    @WebMethod(operationName = "updateSpotFree")
    public void updateSpotFree(int spot_id) {
        ParkingLotDAO parkingLotDAO = new ParkingLotDAO();

        parkingLotDAO.setLotAsFree(spot_id);
    }
}
