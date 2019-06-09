package com.zosiaowsiak.parking.Database;

import com.zosiaowsiak.parking.Models.Employee;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAO {

    private EntityManagerFactory factory= Persistence.createEntityManagerFactory("org.hibernate.tutorial.jpa");
    private EntityManager em = factory.createEntityManager();

    public List<Employee> getAllEmployees(){
        List<Employee> result = new ArrayList<Employee>();
        try{
            TypedQuery<Employee> q = em.createQuery("SELECT e FROM Employee e", Employee.class);
            result = q.getResultList();
        }catch (Exception e){
            System.err.println("Error while retrieving data: " + e);
        }

        return result;
    }
}
