package com.zosiaowsiak.parking.Contracts;

import java.util.List;

public interface MessageStorage {

    void addMessage(String msg);
    List<String> getMessages(String employeeLogin);
}
