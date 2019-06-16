package com.zosiaowsiak.parking.Database;

import com.zosiaowsiak.parking.Models.Employee;
import com.zosiaowsiak.parking.hash.Util;

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

    public Employee getEmployeeByName(String name) {
        List<Employee> employees = getAll();
        for (Employee e : employees){
            if (e.getLogin().equals(name)){
                return e;
            }
        }
        return null;
    }


    public boolean changePassword(String login, String oldPass, String newPass) {
        Employee employee = getEmployeeByName(login);

        String hashedNewPassword = Util.createPasswordHash("MD5", Util.BASE64_ENCODING, null,
                null, newPass);

        String hashedOldPassword = Util.createPasswordHash("MD5", Util.BASE64_ENCODING, null,
                null, oldPass);

        try {
            Employee foundEmployee = em.find(Employee.class, employee.getId());
            if (foundEmployee.getPass().equals(hashedOldPassword)) {
                if(!em.getTransaction().isActive()) em.getTransaction().begin();
                foundEmployee.setPass(hashedNewPassword);
                em.getTransaction().commit();
                return true;
            } else {
                System.out.println("Złe hasło");
                return false;
            }
        } catch (Exception e) {
            em.getTransaction().rollback();
            System.err.println("Error when trying to update data in database: " + e);
        }
        return false;
    }
}
