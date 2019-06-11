package com.zosiaowsiak.parking.Views;


import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
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
    private String newUserLogin;
    private String newUserPass;
    private Integer newUserArea;

    @EJB(lookup = "java:global/server/DatabaseController")
    private DatabaseControllerInterface databaseController;

    public List<ParkingLot> getLots(){

        //TODO tutaj kiedyś powinno się te miejsca ze względu na zalogowaną rozróżniać

        return databaseController.getLots();
    }

    public void addEmployee(){

        databaseController.addEmployee(newUserLogin, newUserPass, newUserArea);
    }


    public String getNewUserLogin() {
        return newUserLogin;
    }

    public void setNewUserLogin(String newUserLogin) {
        this.newUserLogin = newUserLogin;
    }

    public String getNewUserPass() {
        return newUserPass;
    }

    public void setNewUserPass(String newUserPass) {
        this.newUserPass = newUserPass;
    }

    public Integer getNewUserArea() {
        return newUserArea;
    }

    public void setNewUserArea(Integer newUserArea) {
        this.newUserArea = newUserArea;
    }
}
