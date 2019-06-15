package com.zosiaowsiak.parking.Database;

import com.zosiaowsiak.parking.Models.ParkingLot;
import com.zosiaowsiak.parking.Models.Ticket;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.sql.Timestamp;

public class ParkingLotDAO {

    private EntityManagerFactory factory= Persistence.createEntityManagerFactory("org.hibernate.tutorial.jpa");
    private EntityManager em = factory.createEntityManager();


    public void setLotAsFree(Integer lotId) {
        try{

            ParkingLot foundLot = em.find(ParkingLot.class, lotId);
            if(!em.getTransaction().isActive()) em.getTransaction().begin();
            foundLot.setIsoccupied(false);
            em.getTransaction().commit();
            System.out.println("Updated to free lot nr: " + lotId);

        }catch(Exception e){

            em.getTransaction().rollback();
            System.err.println("Error when trying to update data in database: " + e);

        }
    }

    public void setLotAsTaken(Integer lotId) {
        try{
            ParkingLot foundLot = em.find(ParkingLot.class, lotId);
            if(!em.getTransaction().isActive()) em.getTransaction().begin();
            foundLot.setIsoccupied(true);
            Date date= new Date();
            long time = date.getTime();
            Timestamp ts = new Timestamp(time);
            foundLot.setArrivalTime(ts);
            em.getTransaction().commit();
            System.out.println("Updated to occupied lot nr: " + lotId);

        }catch(Exception e){

            em.getTransaction().rollback();
            System.err.println("Error when trying to update data in database: " + e);

        }
    }

    public List<ParkingLot> getAllLots(){
        List<ParkingLot> result = new ArrayList<ParkingLot>();


        try{
            TypedQuery<ParkingLot> q = em.createQuery("SELECT e FROM ParkingLot e", ParkingLot.class);
            result = q.getResultList();
        }catch (Exception e){
            System.err.println("Error while retrieving data: " + e);
        }
        return result;
    }


    public List<ParkingLot> getLotsByArea(Integer area) {
        List<ParkingLot> parkingLots = getAllLots();
        List<ParkingLot> lotsByArea = new ArrayList<>();
        for(ParkingLot pl : parkingLots){
            if(pl.getArea().equals(area)){
                lotsByArea.add(pl);
            }
        }
        return lotsByArea;
    }

    public List<Integer> getLotsIdByArea(Integer area) {
        List<ParkingLot> parkingLots = getAllLots();
        List<Integer> lotsIdByArea = new ArrayList<>();
        for(ParkingLot pl : parkingLots){
            if(pl.getArea().equals(area)){
                lotsIdByArea.add(pl.getId());
            }
        }
        return lotsIdByArea;
    }

    public ParkingLot getLotById(Integer lotId) {
        List<ParkingLot> lots = getAllLots();
        for(ParkingLot l : lots){
            if(l.getId() == lotId){
                return l;
            }
        }
        return null;
    }

    public boolean hasValidTicket(ParkingLot lot) {
        TicketDAO ticketDAO = new TicketDAO();
        List<Ticket> tickets = ticketDAO.getAll();
        System.out.println("SPRAWDZAM CZY VALID TICKET...");

        return tickets
                .stream()
                .filter(t -> t.getLotId() == lot.getId())
                .anyMatch(ParkingLotDAO::isExpired);
    }

    private static boolean isExpired(Ticket ticket) {
        Date now = new Date();
        return ticket.getEndTime().after(now);
    }

    public List<Integer> getAllTakenLotsIds() {
        List<ParkingLot> lots = getAllLots();
        List<Integer> taken = new ArrayList<>();

        for(ParkingLot parkingLot : lots){
            if(parkingLot.getIsoccupied()){
                taken.add(parkingLot.getId());
            }
        }
        return taken;
    }

    public List<Integer> getAllFreeLotsIds() {
        List<ParkingLot> lots = getAllLots();
        List<Integer> free = new ArrayList<>();

        for(ParkingLot parkingLot : lots){
            if(!parkingLot.getIsoccupied()){
                free.add(parkingLot.getId());
            }
        }
        return free;
    }
}
