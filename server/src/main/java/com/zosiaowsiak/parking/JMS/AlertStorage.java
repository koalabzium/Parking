package com.zosiaowsiak.parking.JMS;

import com.zosiaowsiak.parking.Contracts.AlertStorageInterface;
import com.zosiaowsiak.parking.Database.DatabaseController;
import com.zosiaowsiak.parking.Models.Employee;
import com.zosiaowsiak.parking.Models.ParkingLot;


import javax.ejb.Remote;
import javax.ejb.Stateless;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
//import java.util.stream.Collectors;

@Remote(AlertStorageInterface.class)
@Stateless
public class AlertStorage implements AlertStorageInterface {

    private List<String> messages = new ArrayList<String>();
    private DatabaseController databaseController = new DatabaseController();

    public void addMessage(String msg){

        messages.add(msg);
    }

    public List<String> getMessages(String employeeLogin) {
        //najpierw swoje 19, potem dwunastki aż zos2 nie odebrała swojej 12
        List<String> res = new ArrayList<String>();

        Employee employee = databaseController.getEmployeeByName(employeeLogin);
        for(String m : messages){
            ParkingLot parkingLot = databaseController.getLotById(Integer.parseInt(m));
            if(parkingLot.getArea().equals(employee.getArea())){
                res.add(m);
            }
        }
        Iterator<String> iter = messages.iterator();
        while (iter.hasNext()) {
            String str = iter.next();
            ParkingLot parkingLot = databaseController.getLotById(Integer.parseInt(str));
            if (parkingLot.getArea().equals(employee.getArea()))
                iter.remove();
        }

        return res;
    }

}