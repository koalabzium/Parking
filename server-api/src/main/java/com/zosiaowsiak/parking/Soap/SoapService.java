package com.zosiaowsiak.parking.Soap;

import javax.jws.WebMethod;
import javax.jws.WebService;

//@WebService
public interface SoapService {


//    @WebMethod
    public void freeLot(Integer lotId);

//    @WebMethod
    public void takeLot(Integer lotId);
}
