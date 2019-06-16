package com.zosiaowsiak.parking.Contracts;

import com.zosiaowsiak.parking.Models.Employee;
import com.zosiaowsiak.parking.Models.ParkingLot;
import com.zosiaowsiak.parking.Models.Ticket;

import java.util.List;

public interface DatabaseControllerInterface {
    List<Employee> getEmployees();
    List<ParkingLot> getLots();

    void addEmployee(String newUserLogin, String newUserPass, Integer newUserArea);

    void setLotAsTaken(Integer lotId);
    void setLotAsFree(Integer lotId);

    Employee getEmployeeByName(String name);

    List<ParkingLot> getLotsByArea(Integer area);

    List<Ticket> getActiveTickets();

    List<Ticket> getActiveTicketsByArea(Integer area);

    void tempSendMessage();

    ParkingLot getLotById(Integer id);

    boolean changePassword(String employee, String oldPass, String newPass);

    List<String> getEmployeesLogins();
}
