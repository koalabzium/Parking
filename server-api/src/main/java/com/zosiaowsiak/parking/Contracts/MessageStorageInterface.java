package com.zosiaowsiak.parking.Contracts;

import javax.ejb.Local;
import javax.ejb.Remote;
import java.util.List;

@Local
public interface MessageStorageInterface {

    void addMessage(String msg);
    List<String> getMessages(int employeeID);
}
