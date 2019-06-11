package com.zosiaowsiak.parking.Rest.zosiaowsiak;

import com.zosiaowsiak.parking.Soap.PersonService;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import java.net.MalformedURLException;
import java.net.URL;


public class SOAPPublisherClient {

    private PersonService ps;

    public SOAPPublisherClient() throws MalformedURLException {
//        soapPublisher.publish();
//        System.out.println("PUBLISHED");
        URL wsdlURL = new URL("http://localhost:1238/ws/person?wsdl");
        //check above URL in browser, you should see WSDL file

        //creating QName using targetNamespace and name
        QName qname = new QName("http://zosiaowsiak.Views.parking.zosiaowsiak.com/", "PersonServiceImplService");

        Service service = Service.create(wsdlURL, qname);

        //We need to pass interface and model beans to client
        ps = service.getPort(PersonService.class);
    }

    public PersonService getPs() {
        return ps;
    }

//    public static void main(String[] args) throws MalformedURLException {
//
//        SOAPPublisher soapPublisher = new SOAPPublisher();
////        soapPublisher.publish();
////        System.out.println("PUBLISHED");
//        URL wsdlURL = new URL("http://localhost:1222/ws/person?wsdl");
//        //check above URL in browser, you should see WSDL file
//
//        //creating QName using targetNamespace and name
//        QName qname = new QName("http://zosiaowsiak.Views.parking.zosiaowsiak.com/", "PersonServiceImplService");
//
//        Service service = Service.create(wsdlURL, qname);
//
//        //We need to pass interface and model beans to client
//        PersonService ps = service.getPort(PersonService.class);
//
//        Person p1 = new Person(); p1.setName("Pankaj"); p1.setId(1); p1.setAge(30);
//        Person p2 = new Person(); p2.setName("Meghna"); p2.setId(2); p2.setAge(25);
//
////        ps.setLotAsFree(123);
//
//        //add person
//        System.out.println("Add Person Status="+ps.addPerson(p1));
//        System.out.println("Add Person Status="+ps.addPerson(p2));
//
//
//
//        //get person
//        System.out.println(ps.getPerson(1));
//
//        //get all persons
//        System.out.println(Arrays.asList(ps.getAllPersons()));
//
//        //delete person
//        System.out.println("Delete Person Status="+ps.deletePerson(2));
//
//        //get all persons
//        System.out.println(Arrays.asList(ps.getAllPersons()));
//
//    }

}
