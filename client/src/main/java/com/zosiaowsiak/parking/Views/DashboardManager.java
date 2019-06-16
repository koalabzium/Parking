package com.zosiaowsiak.parking.Views;


import com.zosiaowsiak.parking.Contracts.DatabaseControllerInterface;
import com.zosiaowsiak.parking.Models.Employee;
import com.zosiaowsiak.parking.Models.ParkingLot;
import com.zosiaowsiak.parking.Models.Ticket;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import java.io.Serializable;
import java.security.Principal;
import java.util.List;

@Named("dashboardManager")
@SessionScoped
public class DashboardManager implements Serializable {

    private Employee employee;
    private List<ParkingLot> lots;
    private List<Ticket> tickets;


    @EJB(lookup = "java:global/server/DatabaseController")
    private DatabaseControllerInterface databaseController;

    /* TODO
 2. Pracownik strefy ma dostęp do danych dotyczący tylko jego strefy, admin ma dostęp do
wszystkiego i widzi całość danych.
3. Wszyscy użytkownicy mają korzystać z tych samych stron JSF, rozróżnienie ról ma odbywać się w
metodach EJB.
4. Użytkownik powinien mieć możliwość zmiany swojego hasła, natomiast administrator powinien
być w stanie zmieniać hasła wszystkich użytkowników. Hasła nie mogą być trzymane jako plain text.
     */

    @PostConstruct
    public void init(){

        boolean isAdmin = false;
        Integer area = 0;

        if (employee == null) {
            Principal principal = FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal();
            if (principal != null) {
                employee = databaseController.getEmployeeByName(principal.getName());
                isAdmin = employee.getIsadmin();
                area = employee.getArea();
            }
        }
        if (tickets == null){
            if(isAdmin){
                tickets = databaseController.getAllTickets();
            }else{
                tickets = databaseController.getTicketsByArea(area);
            }
        }
        if (lots == null){
            if(isAdmin){
                lots = databaseController.getLots();
            }else{
                lots = databaseController.getLotsByArea(area);
            }
        }
    }

    public String getLoggedName(){
        Principal principal = FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal();
        return principal.getName();
    }

    public List<ParkingLot> getLots(){

        return lots;
    }

    public List<Ticket> getActiveTickets() {

        return tickets;
    }

}
