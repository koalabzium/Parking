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

//    public static void main(String[] args) {
//        String input = "Zosia";
//        System.out.println(input.hashCode());
//        StringBuffer sb = new StringBuffer();
//
//        try {
//            MessageDigest md =
//                    MessageDigest.getInstance("MD5");
//            md.update(input.getBytes());
//            byte[] mdbytes = md.digest();
//
//            //convert the byte to hex format
//            for (int i = 0; i < mdbytes.length; i++) {
//                sb.append(Integer.toString((mdbytes[i] & 0xff)
//                        + 0x100, 16).substring(1));
//            }
//        } catch (NoSuchAlgorithmException e) {
//            e.printStackTrace();
//            System.exit(-1);
//        }
//
//        System.out.println(sb.toString());
//    }




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
