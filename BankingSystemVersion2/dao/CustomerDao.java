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
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;



/**
 *
 * @author adeli
 */
public class CustomerDao {
   String dbUrl="jdbc:mysql://localhost:3306/tuesday";
         String dbUsername="root";
         String dbPassword="waterAndMilk1#";
         
         
         //CRUD Operation
         //create operation
         public int createCustomer(Customer customerObj){
             
         try{
              Connection con = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
             PreparedStatement pst= con .prepareStatement("INSERT INTO customer (nid,names,age,phone_number,account_number)"
                     + " VALUES(?,?,?,?,?)");
            pst.setString(1, customerObj.getNid());
            pst.setString(2, customerObj.getNames());
            pst.setInt(3, customerObj.getAge()); // Use setInt for age
            pst.setString(4, customerObj.getPhone_number());
            pst.setString(5, customerObj.getAccount_number());
             
           // step 3: execute queries
            int rowsAffected = pst.executeUpdate();
            // step 4: close connection
            con.close();
            return rowsAffected;
        }catch(Exception ex){
            ex.printStackTrace();
            return 0;
        }
         
         
         }


    public int updateCustomer(Customer customerObj) {
    try {
        Connection con = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
        PreparedStatement pst = con.prepareStatement(
            "UPDATE customer SET names = ?, age = ?, phone_number = ?, account_number = ? WHERE nid = ?");
        pst.setString(1, customerObj.getNames());
        pst.setInt(2, customerObj.getAge());
        pst.setString(3, customerObj.getPhone_number());
        pst.setString(4, customerObj.getAccount_number());
        pst.setString(5, customerObj.getNid());

        int rowsAffected = pst.executeUpdate();
        con.close();
        return rowsAffected;
    } catch (Exception ex) {
        ex.printStackTrace();
        return 0;
    }
}

public Customer getCustomerById(String id) {
    try {
        Connection con = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
        PreparedStatement pst = con.prepareStatement("SELECT * FROM customer WHERE nid = ?");
        pst.setString(1, id);
        
      ResultSet resultSet = pst.executeQuery();
        if (resultSet.next()) {
            Customer customer = new Customer();
            customer.setNid(resultSet.getString("nid"));
            customer.setNames(resultSet.getString("names"));
            customer.setAge(resultSet.getInt("age"));
            customer.setPhone_number(resultSet.getString("phone_number"));
            customer.setAccount_number(resultSet.getString("account_number"));
            return customer;
        } else {
            return null; // No customer found with that ID
        }
    } catch (Exception ex) {
        ex.printStackTrace();
        return null;
    }
}


         
}