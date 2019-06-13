package com.zosiaowsiak.parking.Views;

import com.zosiaowsiak.parking.Contracts.DatabaseControllerInterface;
import com.zosiaowsiak.parking.Soap.LotSoapServiceInterface;
//import com.zosiaowsiak.parking.Views.zosiaowsiak.SOAPPublisherClient;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

@ManagedBean
@RequestScoped
public class ParkingLotManager {

    private String baseURL = "http://localhost:8080/server/SoapService?wsdl";

    public void takeParkingLot(String parkingLotId) throws IOException {
        String message = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:soap=\"http://Soap.parking.zosiaowsiak.com/\">\n" +
                "   <soapenv:Header/>\n" +
                "   <soapenv:Body>\n" +
                "      <soap:Arrive>\n" +
                "         <!--Optional:-->\n" +
                "         <answer>" + parkingLotId + "</answer>\n" +
                "      </soap:Arrive>\n" +
                "   </soapenv:Body>\n" +
                "</soapenv:Envelope>";


       connectAndSend(message);
    }

    public void freeParkingLot(String parkingLotId) throws IOException {

        System.out.println("ZWALNIAMY");
        String request = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:soap=\"http://Soap.parking.zosiaowsiak.com/\">\n" +
                "   <soapenv:Header/>\n" +
                "   <soapenv:Body>\n" +
                "      <soap:Leave>\n" +
                "         <!--Optional:-->\n" +
                "         <answer>1</answer>\n" +
                "      </soap:Leave>\n" +
                "   </soapenv:Body>\n" +
                "</soapenv:Envelope>";


        connectAndSend(request);

    }

    public void connectAndSend(String request) throws IOException{
        System.out.println("ŁĄCZĘĘĘ...");
        URL url = new URL(baseURL);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("POST");
        con.setDoOutput(true);
        con.setRequestProperty("Content-Type", "text/plain");
        OutputStreamWriter writer = new OutputStreamWriter(con.getOutputStream(), "UTF-8");
        writer.write(request);
        writer.close();

        InputStream inputStream = con.getInputStream();
        con.disconnect();

    }
}
