package com.zosiaowsiak.parking.Rest;


import com.zosiaowsiak.parking.Database.ParkingLotDAO;
import com.zosiaowsiak.parking.Models.ParkingLot;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@Path("/")
public class LotService {

    @GET
    @Path("/hello")
    public String hello() {

        return "Hello";
    }


    @GET
    @Produces("application/json")
    @Path("/takenLots")
    public Response getTakenLots(){
        ParkingLotDAO parkingLotDAO = new ParkingLotDAO();
        List<Integer> lotsIds = parkingLotDAO.getAllTakenLotsIds();

        return Response.status(200).entity(lotsIds).build();
    }

    @GET
    @Produces("application/json")
    @Path("/freeLots")
    public Response getFreeLots(){
        ParkingLotDAO parkingLotDAO = new ParkingLotDAO();
        List<Integer> lotsIds = parkingLotDAO.getAllFreeLotsIds();

        return Response.status(200).entity(lotsIds).build();

    }
}


