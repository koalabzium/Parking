package com.zosiaowsiak.parking.Database;


import com.zosiaowsiak.parking.Models.Ticket;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class TicketDAO {

    private EntityManagerFactory factory= Persistence.createEntityManagerFactory("org.hibernate.tutorial.jpa");
    private EntityManager em = factory.createEntityManager();

    public void add(Ticket ticket) {
        try {
            if(!em.getTransaction().isActive()) em.getTransaction().begin();
            em.persist(ticket);
            em.getTransaction().commit();
            System.out.println("Added to database ticket for lot: " + ticket.getLotId());
        }
        catch(Exception e) {
            em.getTransaction().rollback();
            System.err.println("Error when trying to add to database ticket for lot "+ ticket.getLotId() + ": " + e);
        }

    }

}
