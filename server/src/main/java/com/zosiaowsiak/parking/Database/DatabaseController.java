package com.zosiaowsiak.parking.Database;

import com.zosiaowsiak.parking.Contracts.DatabaseControllerInterface;
import com.zosiaowsiak.parking.Models.Employee;
import com.zosiaowsiak.parking.Models.ParkingLot;

import javax.ejb.Remote;
import javax.ejb.Singleton;
import java.util.List;

@Singleton
@Remote(DatabaseControllerInterface.class)
public class DatabaseController implements DatabaseControllerInterface {

    private ParkingLotDAO parkingLotDAO = new ParkingLotDAO();
    private EmployeeDAO employeeDAO = new EmployeeDAO();

    @Override
    public List<Employee> getEmployees(){
        return employeeDAO.getAllEmployees();
    }

    @Override
    public List<ParkingLot> getLots(){
        return parkingLotDAO.getAllLots();
    }

}
