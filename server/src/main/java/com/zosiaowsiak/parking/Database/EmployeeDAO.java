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

    public List<Employee> getAll(){
        List<Employee> result = new ArrayList<Employee>();
        try{
            TypedQuery<Employee> q = em.createQuery("SELECT e FROM Employee e", Employee.class);
            result = q.getResultList();
        }catch (Exception e){
            System.err.println("Error while retrieving data: " + e);
        }

        return result;
    }

    public void add(Employee employee) {
        try {
            if(!em.getTransaction().isActive()) em.getTransaction().begin();
            em.persist(employee);
            em.getTransaction().commit();
            System.out.println("Added to database" + employee.getLogin());
        }
        catch(Exception e) {
            em.getTransaction().rollback();
            System.err.println("Error when trying to add to database employee "+ employee.getLogin() + ": " + e);
        }

    }
}
