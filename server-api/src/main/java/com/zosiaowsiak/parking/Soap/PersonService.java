package com.zosiaowsiak.parking.Soap;

import com.zosiaowsiak.parking.Models.Person;

import javax.ejb.Remote;
import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.ProtocolException;

@WebService
@SOAPBinding(style = SOAPBinding.Style.RPC)
public interface PersonService {

    @WebMethod
    public boolean setLotAsTaken(Integer lotId) throws IOException;

    @WebMethod
    public boolean setLotAsFree(Integer id) throws IOException;

}