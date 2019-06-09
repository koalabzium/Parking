package com.zosiaowsiak.parking.Views;


import com.zosiaowsiak.parking.Contracts.DatabaseControllerInterface;
import com.zosiaowsiak.parking.Models.ParkingLot;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named("dashboardManager")
@SessionScoped
public class DashboardManager implements Serializable {

    @EJB(lookup = "java:global/server/DatabaseController")
    private DatabaseControllerInterface databaseController;

    public List<ParkingLot> getLots(){

        //TODO tutaj kiedyś powinno się te miejsca ze względu na zalogowaną rozróżniać

        return databaseController.getLots();
    }

}
