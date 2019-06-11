package com.zosiaowsiak.parking.Rest;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;


@ApplicationPath("/")
public class ApplicationRest extends Application {
    private Set<Object> singletons = new HashSet<Object>();
    public ApplicationRest() {
        singletons.add(new TicketService());
        singletons.add(new LotService());
    }
    @Override
    public Set<Object> getSingletons() {
        return singletons;
    }
}
