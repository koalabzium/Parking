package com.zosiaowsiak.parking.Database;

import com.zosiaowsiak.parking.Contracts.DatabaseControllerInterface;
import com.zosiaowsiak.parking.Models.Employee;
import com.zosiaowsiak.parking.Models.ParkingLot;
import com.zosiaowsiak.parking.Models.Ticket;
import com.zosiaowsiak.parking.hash.Util;

import javax.ejb.Remote;
import javax.ejb.Singleton;
import javax.xml.bind.DatatypeConverter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

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
        String pass = Util.createPasswordHash("MD5", Util.BASE64_ENCODING, null,
                null, newUserPass);
        employee.setPass(pass);
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
    public List<Ticket> getActiveTickets() {
        return ticketDAO.getActiveTickets();
    }

    @Override
    public List<Ticket> getActiveTicketsByArea(Integer area) {
        return ticketDAO.getActiveTicketsByArea(area);
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

    @Override
    public boolean changePassword(String employee, String oldPass, String newPass) {
        return employeeDAO.changePassword(employee, oldPass, newPass);
    }



    @Override
    public List<String> getEmployeesLogins() {
        List<Employee> employees = getEmployees();
        List<String> emplLogins = new ArrayList<>();
        for(Employee e: employees){
            emplLogins.add(e.getLogin());
        }
        return emplLogins;
    }

    @Override
    public List<ParkingLot> getFreeLots() {
        parkingLotDAO = new ParkingLotDAO();
        return parkingLotDAO.getAllFreeLots();
    }

    @Override
    public List<ParkingLot> getTakenLots() {
        parkingLotDAO = new ParkingLotDAO();
        return parkingLotDAO.getAllTakenLots();
    }

    @Override
    public List<ParkingLot> getFreeLotsByArea(Integer area) {
        parkingLotDAO = new ParkingLotDAO();
        return parkingLotDAO.getAllFreeLots().stream().filter(parkingLot -> parkingLot.getArea().equals(area)).collect(Collectors.toList());
    }

    @Override
    public List<ParkingLot> getTakenLotsByArea(Integer area) {
        parkingLotDAO = new ParkingLotDAO();
        System.out.println("......................");
        System.out.println("getTakenLotsByArea");
        return parkingLotDAO.getAllTakenLots().stream().filter(parkingLot -> parkingLot.getArea().equals(area)).collect(Collectors.toList());
    }


    public String hashPassword(String password){
        String generatedPassword = "";
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
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
