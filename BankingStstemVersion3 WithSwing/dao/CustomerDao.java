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


}