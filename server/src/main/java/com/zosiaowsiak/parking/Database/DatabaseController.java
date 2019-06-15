package com.zosiaowsiak.parking.Database;

import com.zosiaowsiak.parking.Contracts.DatabaseControllerInterface;
import com.zosiaowsiak.parking.Models.Employee;
import com.zosiaowsiak.parking.Models.ParkingLot;
import com.zosiaowsiak.parking.Models.Ticket;

import javax.ejb.Remote;
import javax.ejb.Singleton;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

@Singleton
@Remote(DatabaseControllerInterface.class)
public class DatabaseController implements DatabaseControllerInterface {

    private ParkingLotDAO parkingLotDAO = new ParkingLotDAO();
    private EmployeeDAO employeeDAO = new EmployeeDAO();
    private TicketDAO ticketDAO = new TicketDAO();

    @Override
    public List<Employee> getEmployees(){
        return employeeDAO.getAll();
    }

    @Override
    public List<ParkingLot> getLots(){
        return parkingLotDAO.getAllLots();
    }

    @Override
    public void addEmployee(String newUserLogin, String newUserPass, Integer newUserArea) {

        Employee employee = new Employee();
        employee.setArea(newUserArea);
        employee.setIsadmin(false);
        employee.setLogin(newUserLogin);
        employee.setPass(newUserPass);
        employeeDAO.add(employee);
    }

    @Override
    public void setLotAsTaken(Integer lotId) {
        System.out.println("USTAWIAM NA TAKEN MIEJSCE " + lotId);
        parkingLotDAO.setLotAsTaken(lotId);
    }

    @Override
    public void setLotAsFree(Integer lotId) {
        System.out.println("USTAWIAM NA FREE MIEJSCE " + lotId);
        parkingLotDAO.setLotAsFree(lotId);
    }

    @Override
    public Employee getEmployeeByName(String name) {
        return employeeDAO.getEmployeeByName(name);
    }

    @Override
    public List<ParkingLot> getLotsByArea(Integer area) {
        return parkingLotDAO.getLotsByArea(area);
    }

    @Override
    public List<Ticket> getAllTickets() {
        return ticketDAO.getAll();
    }

    @Override
    public List<Ticket> getTicketsByArea(Integer area) {
        return ticketDAO.getTicketsByArea(area);
    }

    @Override
    public void tempSendMessage() {

//        MessageSender messageSender = new MessageSender();
//        messageSender.sendMessage("WIADOMOOOOOOOOŚĆ!!!!!!!!!");
    }

    @Override
    public ParkingLot getLotById(Integer id) {
        return parkingLotDAO.getLotById(id);
    }

    public String hashPassword(String password){
        String generatedPassword = "";
        try {
            MessageDigest md = MessageDigest.getInstance("SHA");
            md.update(password.getBytes());
            byte[] bytes = md.digest();
            StringBuilder sb = new StringBuilder();
            for(int i=0; i< bytes.length ;i++)
            {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            generatedPassword = sb.toString();
        }
        catch (NoSuchAlgorithmException e)
        {
            e.printStackTrace();
        }
        System.out.println("HASŁO: " + generatedPassword);
        return generatedPassword;
    }

}
