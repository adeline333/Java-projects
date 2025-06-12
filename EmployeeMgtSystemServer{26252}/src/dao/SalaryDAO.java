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

import model.Salary;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.List;

public class SalaryDAO {

    public String registerSalary(Salary salary) {
        try {
            Session ss = HibernateUtil.getSessionFactory().openSession();
            Transaction tr = ss.beginTransaction();
            ss.save(salary);
            tr.commit();
            ss.close();
            return "Salary saved successfully.";
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public String updateSalary(Salary salary) {
        try {
            Session ss = HibernateUtil.getSessionFactory().openSession();
            Transaction tr = ss.beginTransaction();
            ss.update(salary);
            tr.commit();
            ss.close();
            return "Salary updated successfully.";
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public String deleteSalary(Salary salary) {
        try {
            Session ss = HibernateUtil.getSessionFactory().openSession();
            Transaction tr = ss.beginTransaction();
            ss.delete(salary);
            tr.commit();
            ss.close();
            return "Salary deleted successfully.";
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    public List<Salary> retrieveAllSalaries() {
        Session ss = HibernateUtil.getSessionFactory().openSession();
        List<Salary> salaries = ss.createQuery("from Salary").list();
        ss.close();
        return salaries;
    }

    @SuppressWarnings("unchecked")
    public Salary retrieveSalaryById(String salaryCode) {
    Session ss = HibernateUtil.getSessionFactory().openSession();
    Salary salary = (Salary)ss.get(Salary.class, salaryCode);
    ss.close();
    return salary;
}

}
