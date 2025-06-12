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



import model.User;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.List;

public class UserDAO {

    public String addUser(User user) {
        try {
            Session ss = HibernateUtil.getSessionFactory().openSession();
            Transaction tr = ss.beginTransaction();
            ss.save(user);
            tr.commit();
            ss.close();
            return "User saved successfully.";
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public String updateUser(User user) {
        try {
            Session ss = HibernateUtil.getSessionFactory().openSession();
            Transaction tr = ss.beginTransaction();
            ss.update(user);
            tr.commit();
            ss.close();
            return "User updated successfully.";
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public String deleteUser(User user) {
        try {
            Session ss = HibernateUtil.getSessionFactory().openSession();
            Transaction tr = ss.beginTransaction();
            ss.delete(user);
            tr.commit();
            ss.close();
            return "User deleted successfully.";
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    public List<User> getAllUsers() {
        Session ss = HibernateUtil.getSessionFactory().openSession();
        List<User> users = ss.createQuery("from User").list();
        ss.close();
        return users;
    }

    public User getUserByUsername(String username) {
        Session ss = HibernateUtil.getSessionFactory().openSession();
        User user = (User) ss.get(User.class, username);
        ss.close();
        return user;
    }
}
