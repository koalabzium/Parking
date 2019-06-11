package com.zosiaowsiak.parking.Soap;

import org.json.JSONObject;

import javax.jws.WebService;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;


@WebService(endpointInterface = "com.zosiaowsiak.parking.Soap.LotSoapServiceInterface")
public class LotSoapService implements LotSoapServiceInterface {




    @Override
    public boolean setLotAsTaken(Integer lotId) throws IOException {

        JSONObject json = new JSONObject();
        json.put("parkingLotId",lotId);

        System.out.println(json);
        URL url = new URL("http://localhost:8080/server/" + "lots/arrive");
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


        System.out.println("SETTING AS FREE LOT NR: " + lotId);

        return false;
    }

    @Override
    public boolean setLotAsFree(Integer lotId) throws IOException {

        JSONObject json = new JSONObject();
        json.put("parkingLotId",lotId);

        System.out.println(json);
        URL url = new URL("http://localhost:8080/server/" + "lots/leave");
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
        return true;
    }


}