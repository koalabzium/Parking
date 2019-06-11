package com.zosiaowsiak.parking.Views;

import com.zosiaowsiak.parking.Contracts.DatabaseControllerInterface;
import com.zosiaowsiak.parking.Soap.PersonService;
//import com.zosiaowsiak.parking.Views.zosiaowsiak.SOAPPublisherClient;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

@ManagedBean
@RequestScoped
public class ParkingLotManager {


    @EJB(lookup = "java:global/server/DatabaseController")
    private DatabaseControllerInterface databaseController;



//    private SOAPPublisherClient soapPublisherClient;
    private PersonService ps;

//    public ParkingLotManager() throws MalformedURLException {
//        soapPublisherClient = new SOAPPublisherClient();
//        ps = soapPublisherClient.getPs();
//
//    }

//    public void init() throws MalformedURLException {
//        SOAPPublisher soapPublisher = new SOAPPublisher();
//        soapPublisher.publish();
//        System.out.println("PUBLISHED");
//        URL wsdlURL = new URL("http://localhost:1238/ws/person?wsdl");
//        //check above URL in browser, you should see WSDL file
//
//        //creating QName using targetNamespace and name
//        QName qname = new QName("http://zosiaowsiak.Views.parking.zosiaowsiak.com/", "PersonServiceImplService");
//
//        Service service = Service.create(wsdlURL, qname);
//
//        //We need to pass interface and model beans to client
//        PersonService ps = service.getPort(PersonService.class);
//    }

    public void takeParkingLot(String parkingLotId) throws IOException {
        Integer lotId = Integer.parseInt(parkingLotId);
        System.out.println("ŁĄCZĘ Z PS.SETLOTASTAKEN");
        URL wsdlURL = new URL("http://localhost:5555/ws/person?wsdl");
        QName qname = new QName("http://Soap.parking.zosiaowsiak.com/", "LotSoapServiceService");
        Service service = Service.create(wsdlURL, qname);
        ps = service.getPort(PersonService.class);
        boolean x = ps.setLotAsTaken(lotId);
        System.out.println(x);

    }

    public void freeParkingLot(String parkingLotId) throws IOException {
        Integer lotId = Integer.parseInt(parkingLotId);
        System.out.println("ŁĄCZĘ Z PS.SETLOTASFREE");
        URL wsdlURL = new URL("http://localhost:5555/ws/person?wsdl");

        QName qname = new QName("http://Soap.parking.zosiaowsiak.com/", "LotSoapServiceService");

        Service service = Service.create(wsdlURL, qname);

        ps = service.getPort(PersonService.class);
        System.out.println(ps.setLotAsFree(lotId));

    }
}
