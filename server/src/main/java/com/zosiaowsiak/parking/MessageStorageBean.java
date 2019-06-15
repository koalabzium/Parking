package com.zosiaowsiak.parking;

import com.zosiaowsiak.parking.Contracts.MessageStorage;
import com.zosiaowsiak.parking.Database.DatabaseController;
import com.zosiaowsiak.parking.Database.ParkingLotDAO;
import com.zosiaowsiak.parking.Models.ParkingLot;


import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import java.util.ArrayList;
import java.util.List;
//import java.util.stream.Collectors;

@Remote(MessageStorage.class)
@Stateless
public class MessageStorageBean implements MessageStorage {

    private List<String> messages = new ArrayList<String>();
    private DatabaseController databaseController = new DatabaseController();

    public void addMessage(String msg){

        messages.add(msg);
    }

    public List<String> getMessages(int employeeID) {
        List<String> res = new ArrayList<String>(messages);

//        for(String m : messages){
//            ParkingLot parkingLot = databaseController.getLotById();
//        }

        messages.clear();
        return res;
    }

}