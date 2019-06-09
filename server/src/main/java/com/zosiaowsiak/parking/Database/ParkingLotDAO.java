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

}
