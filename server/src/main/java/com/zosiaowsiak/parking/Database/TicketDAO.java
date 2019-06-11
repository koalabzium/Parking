package com.zosiaowsiak.parking.Database;


import com.zosiaowsiak.parking.Models.Ticket;
import com.zosiaowsiak.parking.Models.Ticket;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

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

    public List<Ticket> getAll() {
        List<Ticket> result = new ArrayList<Ticket>();


        try{
            TypedQuery<Ticket> q = em.createQuery("SELECT e FROM Ticket e", Ticket.class);
            result = q.getResultList();
        }catch (Exception e){
            System.err.println("Error while retrieving data: " + e);
        }
        return result;
    }

    public List<Ticket> getTicketsByArea() {
        List<Ticket> tickets = getAll();
        List<Ticket> ticketsByArea = new ArrayList<>();

        return null;
    }
}
