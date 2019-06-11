package com.zosiaowsiak.parking.Contracts;

import com.zosiaowsiak.parking.Models.Employee;
import com.zosiaowsiak.parking.Models.ParkingLot;

import java.util.List;

public interface DatabaseControllerInterface {
    List<Employee> getEmployees();
    List<ParkingLot> getLots();

    void addEmployee(String newUserLogin, String newUserPass, Integer newUserArea);

    void setLotAsTaken(Integer lotId);
    void setLotAsFree(Integer lotId);
}
