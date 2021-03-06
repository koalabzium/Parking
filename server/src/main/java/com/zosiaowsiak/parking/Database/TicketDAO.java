package com.zosiaowsiak.parking.Database;


import com.zosiaowsiak.parking.Models.Ticket;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.Date;
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

    public List<Ticket> getActiveTicketsByArea(Integer area) {
        List<Ticket> tickets = getActiveTickets();
        List<Ticket> ticketsByArea = new ArrayList<>();
        ParkingLotDAO parkingLotDAO = new ParkingLotDAO();
        List<Integer> lotsIds = parkingLotDAO.getLotsIdByArea(area);
        for(Ticket t: tickets){
            if(lotsIds.contains(t.getLotId())){
                ticketsByArea.add(t);
            }
        }

        return ticketsByArea;
    }

    public Ticket getTicketById(Integer ticketId) {
        List<Ticket> tickets = getAll();
        for(Ticket ticket : tickets){
            if(ticket.getId() == ticketId){
                return ticket;
            }
        }
        return null;
    }

    public List<Ticket> getActiveTickets() {
        List<Ticket> active = new ArrayList<>();
        List<Ticket> tickets = getAll();
        for (Ticket t : tickets){
            if(t.getEndTime().after(new Date())){
                active.add(t);
            }
        }
        return active;
    }
}
