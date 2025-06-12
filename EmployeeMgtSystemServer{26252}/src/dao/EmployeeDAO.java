/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

/**
 *
 * @author adeli
 */


import model.Employee;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.List;

public class EmployeeDAO {

    public String registerEmployee(Employee employee) {
        try {
            Session ss = HibernateUtil.getSessionFactory().openSession();
            Transaction tr = ss.beginTransaction();
            ss.save(employee);
            tr.commit();
            ss.close();
            return "Employee saved successfully.";
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public String updateEmployee(Employee employee) {
        try {
            Session ss = HibernateUtil.getSessionFactory().openSession();
            Transaction tr = ss.beginTransaction();
            ss.update(employee);
            tr.commit();
            ss.close();
            return "Employee updated successfully.";
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public String deleteEmployee(Employee employee) {
        try {
            Session ss = HibernateUtil.getSessionFactory().openSession();
            Transaction tr = ss.beginTransaction();
            ss.delete(employee);
            tr.commit();
            ss.close();
            return "Employee deleted successfully.";
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    public List<Employee> retrieveAllEmployees() {
        Session ss = HibernateUtil.getSessionFactory().openSession();
        List<Employee> employees = ss.createQuery("from Employee").list();
        ss.close();
        return employees;
    }

    @SuppressWarnings("unchecked")
    public Employee retrieveEmployeeById(String empId) {
    Session ss = HibernateUtil.getSessionFactory().openSession();
    Employee employee = (Employee)ss.get(Employee.class, empId);
    ss.close();
    return employee;
}

    
     

}
