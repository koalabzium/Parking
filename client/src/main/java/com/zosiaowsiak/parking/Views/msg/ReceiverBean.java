package com.zosiaowsiak.parking.Views.msg;

import com.zosiaowsiak.parking.Contracts.DatabaseControllerInterface;
import com.zosiaowsiak.parking.Contracts.AlertStorageInterface;
import com.zosiaowsiak.parking.Models.Employee;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import java.security.Principal;
import java.util.List;

@ManagedBean(name = "notificationView")
@SessionScoped
public class ReceiverBean {

    private Employee employee;

    @EJB(lookup = "java:global/server/AlertStorage")
    AlertStorageInterface alertStorage;

    @EJB(lookup = "java:global/server/DatabaseController")
    DatabaseControllerInterface databaseController;

    List<String> messages;

    @PostConstruct
    public void init(){
        if (employee == null) {
            Principal principal = FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal();
            if (principal != null) {
                employee = databaseController.getEmployeeByName(principal.getName());
            }
        }
        messages = alertStorage.getMessages(employee.getLogin());
    }

    public List<String> getMessages() {

        messages.addAll(alertStorage.getMessages(employee.getLogin()));
        return messages;
    }

    public void setMessages(List<String> messages) {
        this.messages = messages;
    }

    public String getEmployeeName(){
        return employee.getLogin();
    }
}
