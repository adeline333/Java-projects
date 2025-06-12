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


import model.Department;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.List;

public class DepartmentDAO {

    public String addDepartment(Department dept) {
        try {
            Session ss = HibernateUtil.getSessionFactory().openSession();
            Transaction tr = ss.beginTransaction();
            ss.save(dept);
            tr.commit();
            ss.close();
            return "Department saved successfully.";
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public String updateDepartment(Department dept) {
        try {
            Session ss = HibernateUtil.getSessionFactory().openSession();
            Transaction tr = ss.beginTransaction();
            ss.update(dept);
            tr.commit();
            ss.close();
            return "Department updated successfully.";
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public String deleteDepartment(Department dept) {
        try {
            Session ss = HibernateUtil.getSessionFactory().openSession();
            Transaction tr = ss.beginTransaction();
            ss.delete(dept);
            tr.commit();
            ss.close();
            return "Department deleted successfully.";
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    public List<Department> getAllDepartments() {
        Session ss = HibernateUtil.getSessionFactory().openSession();
        List<Department> departments = ss.createQuery("from Department").list();
        ss.close();
        return departments;
    }

    public Department getDepartmentByCode(String deptCode) {
        Session ss = HibernateUtil.getSessionFactory().openSession();
        Department dept = (Department) ss.get(Department.class, deptCode);
        ss.close();
        return dept;
    }

    // For the department combo box
    @SuppressWarnings("unchecked")
    public List<String> getAllDepartmentNames() {
        Session ss = HibernateUtil.getSessionFactory().openSession();
        List<String> names = ss.createQuery("select dept.deptName from Department dept").list();
        ss.close();
        return names;
    }
}
