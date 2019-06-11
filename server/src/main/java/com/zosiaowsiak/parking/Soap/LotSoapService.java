package com.zosiaowsiak.parking.Soap;

import com.zosiaowsiak.parking.Database.DatabaseController;
import com.zosiaowsiak.parking.Database.ParkingLotDAO;
import com.zosiaowsiak.parking.Models.Person;
import org.hibernate.dialect.Database;

import javax.jws.WebService;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;


@WebService(endpointInterface = "com.zosiaowsiak.parking.Soap.PersonService")
public class LotSoapService implements PersonService {


    @Override
    public boolean setLotAsTaken(Integer lotId) {
        System.out.println("SETTING AS FREE LOT NR: " + lotId);
//        DatabaseController databaseController = new DatabaseController();
//        databaseController.setLotAsTaken(lotId);
//        if(persons.get(p.getId()) != null) return false;
//        persons.put(p.getId(), p);
        return false;
    }

    @Override
    public boolean setLotAsFree(Integer lotId) {
        System.out.println("SETTING AS TAKEN LOT NR: " + lotId);
//        DatabaseController databaseController = new DatabaseController();
//        databaseController.setLotAsFree(lotId);
//        if(persons.get(id) == null) return false;
//        persons.remove(id);
        return true;
    }


}