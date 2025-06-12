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



import model.Role;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.List;

public class RoleDAO {

    public String addRole(Role role) {
        try {
            Session ss = HibernateUtil.getSessionFactory().openSession();
            Transaction tr = ss.beginTransaction();
            ss.save(role);
            tr.commit();
            ss.close();
            return "Role saved successfully.";
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public String updateRole(Role role) {
        try {
            Session ss = HibernateUtil.getSessionFactory().openSession();
            Transaction tr = ss.beginTransaction();
            ss.update(role);
            tr.commit();
            ss.close();
            return "Role updated successfully.";
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public String deleteRole(Role role) {
        try {
            Session ss = HibernateUtil.getSessionFactory().openSession();
            Transaction tr = ss.beginTransaction();
            ss.delete(role);
            tr.commit();
            ss.close();
            return "Role deleted successfully.";
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    public List<Role> getAllRoles() {
        Session ss = HibernateUtil.getSessionFactory().openSession();
        List<Role> roles = ss.createQuery("from Role").list();
        ss.close();
        return roles;
    }

    public Role getRoleById(String roleId) {
        Session ss = HibernateUtil.getSessionFactory().openSession();
        Role role = (Role) ss.get(Role.class, roleId);
        ss.close();
        return role;
    }
}
