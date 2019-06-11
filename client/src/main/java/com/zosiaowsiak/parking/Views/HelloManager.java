package com.zosiaowsiak.parking.Views;


import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import java.io.Serializable;
import java.security.Principal;

@Named()
@SessionScoped
public class HelloManager implements Serializable {
    public String getName(){
        Principal principal = FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal();
        return principal.getName();
    }
}
