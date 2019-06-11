package com.zosiaowsiak.parking.Views;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;

import com.google.gson.Gson;
import com.zosiaowsiak.parking.Contracts.DatabaseControllerInterface;
import com.zosiaowsiak.parking.Models.ParkingLot;
import org.json.JSONObject;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


@ManagedBean
@RequestScoped
public class BuyingTicketManager {

    private String baseURL = "http://localhost:8080/server/";

    public void buyTicket(Integer parkingLotId, String stayDurationInHours) throws IOException {

        JSONObject json = new JSONObject();
        json.put("parkingLotId",parkingLotId);
        json.put("stayDurationInHours",stayDurationInHours);

        System.out.println(json);
        URL url = new URL(baseURL + "tickets");
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("POST");
        con.setDoOutput(true);
        con.setRequestProperty("Content-Type", "application/json");
        OutputStreamWriter writer = new OutputStreamWriter(con.getOutputStream(), "UTF-8");
        writer.write(json.toString());
        writer.close();

        InputStream inputStream = con.getInputStream();
        BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
        StringBuffer jsonString = new StringBuffer();
        String line;
        while ((line = br.readLine()) != null) {
            jsonString.append(line);
        }
        br.close();

        System.out.println(jsonString);
        con.disconnect();

//        return "success";
    }

    public List<Integer> getFreeLotIds() throws IOException {

        // TODO return only free lots

        URL url = new URL(baseURL + "lots");

        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        con.setDoOutput(true);
        con.setRequestProperty("Content-Type", "application/json");

        InputStreamReader isr = new InputStreamReader(con.getInputStream());
        BufferedReader br = new BufferedReader(isr);
        StringBuffer inputJson = new StringBuffer();
        String line;
        while ((line = br.readLine()) != null) {
            inputJson.append(line);
        }
        br.close();
        con.disconnect();

        System.out.println(inputJson);

        Gson googleJson = new Gson();
        ArrayList<Double> lotsDouble = googleJson.fromJson(inputJson.toString(), ArrayList.class);

        ArrayList<Integer> lotIds = new ArrayList<Integer>();
        for(Double d : lotsDouble){
            lotIds.add(d.intValue());
        }

        return lotIds;

    }


}
