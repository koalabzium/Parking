package com.zosiaowsiak.parking.Views;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import com.google.gson.Gson;
import org.json.JSONObject;

import java.io.*;
import java.net.HttpURLConnection;
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

        //TODO wywal to zbieranie inputu...

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

    public List<Integer> getTakenLots() throws IOException {
        URL url = new URL(baseURL + "takenLots");
        return sendGet(url);

    }

    public List<Integer> getFreeLots() throws IOException{
        URL url = new URL(baseURL + "freeLots");
        return sendGet(url);

    }

    public List<Integer> sendGet(URL url) throws IOException{
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
