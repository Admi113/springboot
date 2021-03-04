package com.dmitriy.springboot.springboot.dao;

import com.dmitriy.springboot.springboot.entity.Employee;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class EmployeeDAOimpl implements EmployeeDAO {

    private EntityManager entityManager;

    @Autowired
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override

    public List<Employee> getAllEmployees() {
        Session session = entityManager.unwrap(Session.class);
//    List<Employee> allEmployees = session.createQuery
//                ("from Employee ",
//                        Employee.class)
//            .getResultList();
        Query<Employee> query = session.createQuery("from Employee ",
                Employee.class);
        List<Employee> allEmployees = query.getResultList();
        return allEmployees;
    }
//
    @Override
    public void saveEmployee(Employee employee) {
        Session session = entityManager.unwrap(Session.class);
        session.saveOrUpdate(employee);
    }

    @Override
    public Employee getEmployee(int id) {
        Session session = entityManager.unwrap(Session.class);
        Employee employee = session.get(Employee.class, id);
        return employee;
    }

    @Override
    public void deleteEmployee(int id) {
        Session session = entityManager.unwrap(Session.class);
        Query query = session.createQuery("delete from" +
                " Employee where id= :id");
        query.setParameter("id", id);
        query.executeUpdate();
    }
}
