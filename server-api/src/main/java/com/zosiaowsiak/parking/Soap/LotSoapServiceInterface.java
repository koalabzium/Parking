package com.zosiaowsiak.parking.Soap;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import java.io.IOException;

@WebService
@SOAPBinding(style = SOAPBinding.Style.RPC)
public interface LotSoapServiceInterface {

    @WebMethod
    public boolean setLotAsTaken(Integer lotId) throws IOException;

    @WebMethod
    public boolean setLotAsFree(Integer id) throws IOException;

}