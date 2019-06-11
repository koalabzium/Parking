package com.zosiaowsiak.parking.Views.zosiaowsiak;

import javax.xml.ws.Endpoint;

public class SOAPPublisher {

    public static void main(String[] args) {
        Endpoint.publish("http://localhost:1222/ws/person", new PersonServiceImpl());
    }

}
