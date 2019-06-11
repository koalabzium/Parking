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
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
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

    @POST
    @Consumes("application/json")
    @Path("/arrive")
    public Response lotTaken(InputStream inputStream){
        JSONParser jsonParser = new JSONParser();
        JSONObject json;
        try {
            json = (JSONObject)jsonParser.parse(
                    new InputStreamReader(inputStream, StandardCharsets.UTF_8));
        } catch (IOException | ParseException e) {
            e.printStackTrace();
            return Response.status(500).build();
        }
        Object spotIdObj = json.get("parkingLotId");
        String lotId = spotIdObj.toString();

        ParkingLotDAO parkingLotDAO = new ParkingLotDAO();
        parkingLotDAO.setLotAsTaken(Integer.parseInt(lotId));

        //TODO USTAW JAKOŚ EVENT CZY KOLEJKE CZY COŚ...
        return Response.ok().build();
    }


    @POST
    @Consumes("application/json")
    @Path("/leave")
    public Response lotFreed(InputStream inputStream){
        JSONParser jsonParser = new JSONParser();
        JSONObject json;
        try {
            json = (JSONObject)jsonParser.parse(
                    new InputStreamReader(inputStream, StandardCharsets.UTF_8));
        } catch (IOException | ParseException e) {
            e.printStackTrace();
            return Response.status(500).build();
        }
        Object spotIdObj = json.get("parkingLotId");
        String lotId = spotIdObj.toString();

        ParkingLotDAO parkingLotDAO = new ParkingLotDAO();
        parkingLotDAO.setLotAsFree(Integer.parseInt(lotId));

        //TODO USTAW JAKOŚ EVENT CZY KOLEJKE CZY COŚ...
        return Response.ok().build();
    }
}


