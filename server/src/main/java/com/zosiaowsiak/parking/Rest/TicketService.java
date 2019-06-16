package com.zosiaowsiak.parking.Rest;

import com.zosiaowsiak.parking.AlertManagerBean;
import com.zosiaowsiak.parking.Contracts.AlertManager;
import com.zosiaowsiak.parking.Database.TicketDAO;
import com.zosiaowsiak.parking.Models.Ticket;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.sql.Timestamp;
import java.util.Date;

@Path("/tickets")
public class TicketService {

//    @EJB(lookup = "java:global/server/AlertManagerBean")
//    AlertManager alertManager;


    @POST
    @Consumes("application/json")
    @Path("/")
    public Response buyTicket(InputStream inputStream){
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
        Object durationInHours = json.get("stayDurationInHours");
        String durationStr = durationInHours.toString();
        Float stayDuration = Float.parseFloat(durationStr);
        Ticket ticket = new Ticket();
        Date date = new Date();
        long time = date.getTime();
        Timestamp startTimestamp = new Timestamp(time);

        ticket.setStartTime(startTimestamp);
        ticket.setEndTime(new Timestamp(time + (long) (stayDuration*60*60*1000)));
        ticket.setLotId(Integer.parseInt(spotIdObj.toString()));

        TicketDAO ticketDAO = new TicketDAO();
        ticketDAO.add(ticket);

        new AlertManagerBean().scheduleTicketCheck(ticket);

        return Response.ok().build();
    }


}
