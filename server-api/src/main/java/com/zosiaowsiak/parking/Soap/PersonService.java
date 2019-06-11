package com.zosiaowsiak.parking.Soap;

import com.zosiaowsiak.parking.Models.Person;

import javax.ejb.Remote;
import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import java.net.MalformedURLException;

@WebService
@SOAPBinding(style = SOAPBinding.Style.RPC)
public interface PersonService {

    @WebMethod
    public boolean addPerson(Person p);

    @WebMethod
    public boolean deletePerson(int id);

    @WebMethod
    public Person getPerson(int id);

    @WebMethod
    public Person[] getAllPersons();
//
//    @WebMethod
//    void setLotAsTaken(Integer lotId) throws MalformedURLException;
//
//    @WebMethod
//    void setLotAsFree(Integer lotId) throws MalformedURLException;
}