package com.zosiaowsiak.parking.Rest;


import com.zosiaowsiak.parking.Database.ParkingLotDAO;
import com.zosiaowsiak.parking.Models.ParkingLot;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

@Path("/lots")
public class LotService {

    @GET
    @Path("/hello")
    public String hello() {

        return "Hello";
    }


    @GET
    @Produces("application/json")
    @Path("/")
    public Response getLotIds(){
        System.out.println("GETING LOTS IDS");
        List<Integer> lotsIds = new ArrayList<>();
        ParkingLotDAO parkingLotDAO = new ParkingLotDAO();
        List<ParkingLot> lots = parkingLotDAO.getAllLots();
        if (lots == null || lots.size()==0)
            return Response.status(500).entity("Error while getting Parking Spots from Database").build();

        for (ParkingLot lot : lots){
            lotsIds.add(lot.getId());
        }

        System.out.println(lotsIds);

        return Response.status(200).entity(lotsIds).build();

    }
}


