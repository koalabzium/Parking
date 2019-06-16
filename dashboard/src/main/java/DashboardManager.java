

import com.zosiaowsiak.parking.Contracts.DatabaseControllerInterface;
import com.zosiaowsiak.parking.Models.Employee;
import com.zosiaowsiak.parking.Models.ParkingLot;
import com.zosiaowsiak.parking.Models.Ticket;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import java.io.Serializable;
import java.security.Principal;
import java.util.List;

@ManagedBean
@SessionScoped
public class DashboardManager implements Serializable {
    private String newUserLogin;
    private String newUserPass;
    private Integer newUserArea;

    @EJB(lookup = "java:global/server/DatabaseController")
    private DatabaseControllerInterface databaseController;

    private Employee employee;

    /* TODO
 2. Pracownik strefy ma dostęp do danych dotyczący tylko jego strefy, admin ma dostęp do
wszystkiego i widzi całość danych.
3. Wszyscy użytkownicy mają korzystać z tych samych stron JSF, rozróżnienie ról ma odbywać się w
metodach EJB.
4. Użytkownik powinien mieć możliwość zmiany swojego hasła, natomiast administrator powinien
być w stanie zmieniać hasła wszystkich użytkowników. Hasła nie mogą być trzymane jako plain text.
     */



    public String getLoggedName(){
        Principal principal = FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal();
        return principal.getName();
//        return "ZOSIA";
    }

    public List<ParkingLot> getLots(){
        return databaseController.getLots();

//        Principal principal = FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal();
//        String name = principal.getName();
//        Employee employee = databaseController.getEmployeeByName(name);
//        boolean isAdmin = employee.getIsadmin();
//        System.out.println(employee.getLogin() + " JEST ADMINEM? " + isAdmin);
//        if(isAdmin){
//            return databaseController.getLots();
//        }
//        else{
//            return databaseController.getLotsByArea(employee.getArea());
//        }
    }

    public void addEmployee(){

        databaseController.addEmployee(newUserLogin, newUserPass, newUserArea);
    }

    public Integer ticketsCount(){
        List<Ticket> tickets = databaseController.getAllTickets();
        return tickets.size();
    }


    public List<Ticket> getActiveTickets() {
        return databaseController.getAllTickets();
//        Principal principal = FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal();
//        String name = principal.getName();
//        Employee employee = databaseController.getEmployeeByName(name);
//        boolean isAdmin = employee.getIsadmin();
//        if(isAdmin){
//            return databaseController.getAllTickets();
//        }
//        else{
//            return databaseController.getTicketsByArea(employee.getArea());
//        }
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
