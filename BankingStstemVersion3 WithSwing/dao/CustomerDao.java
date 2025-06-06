/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import model.Customer;


/**
 *
 * @author adeli
 */
public class CustomerDao {
    private String jdbcurl = "jdbc:mysql://localhost:3306/bank_db";
    private String dbUsername = "root";
    private String dbPasswd = "waterAndMilk1#";

    // register new customer
    public int registerCustomer(Customer custObj) {
        try {
            Connection con = DriverManager.getConnection(jdbcurl, dbUsername, dbPasswd);
            String sql = "INSERT INTO customers (nid, names, age, phone_number, account_number) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement pst = con.prepareStatement(sql);

            pst.setString(1, custObj.getNid());
            pst.setString(2, custObj.getNames());
            pst.setInt(3, custObj.getAge());
            pst.setString(4, custObj.getPhone_number());
            pst.setString(5, custObj.getAccount_number());

            int rowsAffected = pst.executeUpdate();

            pst.close();
            con.close();

            return rowsAffected;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return 0;
    }

   

    // update customer 
    public int updateCustomer(Customer custObj) {
        try {
            Connection con = DriverManager.getConnection(jdbcurl, dbUsername, dbPasswd);
            String sql = "UPDATE customers SET names = ?, age = ?, phone_number = ?, account_number = ? WHERE nid = ?";
            PreparedStatement pst = con.prepareStatement(sql);

            pst.setString(1, custObj.getNames());
            pst.setInt(2, custObj.getAge());
            pst.setString(3, custObj.getPhone_number());
            pst.setString(4, custObj.getAccount_number());
            pst.setString(5, custObj.getNid()); // Condition to update based on NID

            int rowsAffected = pst.executeUpdate();

            pst.close();
            con.close();

            return rowsAffected;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return 0;
    }

    // delete by nid
    public int deleteCustomer(Customer custObj) {
    try {
        Connection con = DriverManager.getConnection(jdbcurl, dbUsername, dbPasswd);
        String sql = "DELETE FROM customers WHERE nid = ?";
        PreparedStatement pst = con.prepareStatement(sql);

        pst.setString(1, custObj.getNid()); 

        int rowsAffected = pst.executeUpdate();

        con.close(); 

        return rowsAffected;
    } catch (Exception ex) {
        ex.printStackTrace();
    }
    return 0;
}


public Customer findCustomerById(Customer custObj) {
    try {
        Connection con = DriverManager.getConnection(jdbcurl, dbUsername, dbPasswd);
        String sql = "SELECT * FROM customers WHERE nid = ?";
        PreparedStatement pst = con.prepareStatement(sql);
        pst.setString(1, custObj.getNid());
        ResultSet rs = pst.executeQuery();

        Customer theCustomer = new Customer();
        if (rs.next()) {
            theCustomer.setAccount_number(rs.getString("account_number"));
            theCustomer.setAge(rs.getInt("age")); 
            theCustomer.setNames(rs.getString("names")); 
            theCustomer.setPhone_number(rs.getString("phone_number")); 
            theCustomer.setNid(rs.getString("nid"));
        }

        
        con.close();

        return theCustomer;
    } catch (Exception ex) {
        ex.printStackTrace();
    }
    return null; 
}

    

public List<Customer> findAllCustomer() {
    try {
        Connection con = DriverManager.getConnection(jdbcurl, dbUsername, dbPasswd);
        String sql = "SELECT * FROM customers"; 
        PreparedStatement pst = con.prepareStatement(sql);
        
        ResultSet rs = pst.executeQuery();
        List<Customer> customerList = new ArrayList<>();
        
        while (rs.next()) { 
            Customer custObj = new Customer();
            custObj.setAccount_number(rs.getString("account_number"));
            custObj.setNames(rs.getString("names"));
            custObj.setPhone_number(rs.getString("phone_number"));
            custObj.setAge(rs.getInt("age"));
            custObj.setNid(rs.getString("nid"));
            customerList.add(custObj);
        }
        
       
        con.close(); 
        return customerList; 
    } catch (Exception ex) {
        ex.printStackTrace();
    }
    
    return null;
}



}