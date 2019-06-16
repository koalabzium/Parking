package com.zosiaowsiak.parking.Contracts;

import java.util.List;

public interface AlertStorageInterface {

    void addMessage(String msg);
    List<String> getMessages(String employeeLogin);
}
