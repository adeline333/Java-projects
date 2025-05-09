/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package app;

import java.sql.Connection;
import java.sql.DriverManager;
import static java.sql.DriverManager.getConnection;
import java.util.Scanner;
import java.sql.*;


/**
 *
 * @author adeli
 */


public class Banking {
    //variable declariation
   
    
    public static void main(String args[]){
        
         Scanner sc = new Scanner(System.in);
         boolean condition=true;
         String nationalId;
         String names;
         int age;
         String phoneNumber;
         String accountNumber;
         
         String db_url="jdbc:mysql://localhost:3306/tuesday";
         String username="root";
         String password="waterAndMilk1#";
         
         while(condition){
              System.out.println("===============================");
              System.out.println(" BANKING SYSTEM ");
              System.out.println("===============================");
              System.out.println("1. Register a customer");
              System.out.println("2. Update  customer information");
              System.out.println("3. Find customer by National ID");
              System.out.println("4. Delete customer");
              System.out.println("5. Find all customers");
              System.out.println("6. EXIT");
              System.out.println("--------------------------------");
              System.out.println("Choose: ");

               int choice= sc.nextInt();
               String option;
               switch(choice){
                   case 1:
                       System.out.println("Customer Registration selected ");
                        System.out.println("------------------------------- ");
                         System.out.print("Enter natinal ID: ");
                         nationalId=sc.next();
                               
                         System.out.print("Enter your name: ");
                         names=sc.next();
                         
                         System.out.print("Enter your age: ");
                         age=sc.nextInt();
                         
                         System.out.print("Enter your phone number: ");
                         phoneNumber=sc.next();
                         
                         System.out.print("Enter your account number: ");
                         accountNumber=sc.next();
                       
                         try{
                         //step 0:Surround with try and catch
                         //step 1: Create /Establish a connection
                          Connection con = DriverManager.getConnection(db_url, username, password);
                         //step 2: Create statement
                         Statement st=con.createStatement();
                         //step 3: Execute the statement
                         String sql= "INSERT INTO CUSTOMER(nid, names, age, phone_number, account_number) VALUES('" + nationalId + "','" + names + "'," + age + ",'" + phoneNumber + "','" + accountNumber + "')";
                         int rowsAffected=st.executeUpdate(sql);
                         //step 4: Close connection
                         con.close();
                         if(rowsAffected>0){
                              System.out.println("Data inserted");  
                         }
                         else{
                             System.out.println("Data not inserted"); 
                         }
                         }catch(Exception ex){
                         ex.printStackTrace();
                         }
                         
                       
                       System.out.println("Enter yes to continue or No to quit: ");  
                        option=sc.next();
                        if(option.equalsIgnoreCase("yes")){
                            condition =true;
                        }
                        
                        else if(option.equalsIgnoreCase("No")){
                            condition= false;
                        }
                        else{
                             System.out.println("Wrong Answer  \n Systen decided to continue");  
                              condition=false;
                        }
                       break;
                       
                        case 2:
    System.out.println("Updating customer information");
    System.out.print("Enter National ID of the customer to update: ");
    nationalId = sc.next();

    try {
        // Step 1: Establish a connection
        Connection con = DriverManager.getConnection(db_url, username, password);

        // Step 2: Check if customer exists
        String checkSql = "SELECT * FROM Customer WHERE nId = ?";
        PreparedStatement checkStmt = con.prepareStatement(checkSql);
        checkStmt.setString(1, nationalId);
        ResultSet rs = checkStmt.executeQuery();

        if (rs.next()) {
            // Step 3: Ask user for new details
            System.out.print("Enter new name (Leave blank to keep current): ");
            sc.nextLine();  // Consume newline
            String newName = sc.nextLine();
            if (newName.isEmpty()) newName = rs.getString("names");

            System.out.print("Enter new age (Leave blank to keep current): ");
            String newAgeInput = sc.nextLine();
            int newAge = newAgeInput.isEmpty() ? rs.getInt("age") : Integer.parseInt(newAgeInput);

            System.out.print("Enter new phone number (Leave blank to keep current): ");
            String newPhone = sc.nextLine();
            if (newPhone.isEmpty()) newPhone = rs.getString("phone_number");

            System.out.print("Enter new account number (Leave blank to keep current): ");
            String newAccount = sc.nextLine();
            if (newAccount.isEmpty()) newAccount = rs.getString("account_number");

            // Step 4: Update customer in database
            String updateSql = "UPDATE Customer SET names = ?, age = ?, phone_number = ?, account_number = ? WHERE nId = ?";
            PreparedStatement updateStmt = con.prepareStatement(updateSql);
            updateStmt.setString(1, newName);
            updateStmt.setInt(2, newAge);
            updateStmt.setString(3, newPhone);
            updateStmt.setString(4, newAccount);
            updateStmt.setString(5, nationalId);

            int rowsUpdated = updateStmt.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Customer updated successfully!");
            } else {
                System.out.println("Update failed!");
            }
        } else {
            System.out.println("Customer with ID " + nationalId + " not found.");
        }

        con.close();
    } catch (Exception ex) {
        ex.printStackTrace();
    }
    break;

    

            
       
                   
    }
}
