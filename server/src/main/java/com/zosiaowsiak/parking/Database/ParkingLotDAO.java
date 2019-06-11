package com.zosiaowsiak.parking.Database;

import com.zosiaowsiak.parking.Models.ParkingLot;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

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
}
