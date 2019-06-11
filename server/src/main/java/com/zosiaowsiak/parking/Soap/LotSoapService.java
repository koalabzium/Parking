package com.zosiaowsiak.parking.Soap;

import com.zosiaowsiak.parking.Database.DatabaseController;
import com.zosiaowsiak.parking.Database.ParkingLotDAO;
import com.zosiaowsiak.parking.Models.Person;
import org.hibernate.dialect.Database;
import org.json.JSONObject;

import javax.jws.WebService;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;


@WebService(endpointInterface = "com.zosiaowsiak.parking.Soap.PersonService")
public class LotSoapService implements PersonService {


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