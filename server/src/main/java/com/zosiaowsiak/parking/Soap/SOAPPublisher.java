package com.zosiaowsiak.parking.Soap;

import javax.xml.ws.Endpoint;

public class SOAPPublisher {

    public static void main(String[] args) {
        LotSoapService personService = new LotSoapService();
        Endpoint.publish("http://localhost:5555/ws/person",
                personService);
    }

}
