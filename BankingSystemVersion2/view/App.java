/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.util.Scanner;
import model.Customer;
import dao.CustomerDao;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author adeli
 */
public class App {
    public static void main(String[] args){
        boolean condition =true;
       
        Scanner sc=new Scanner(System.in);
        String names;
        String id;
        String phoneNumber;
        String accountNumber;
        int age;
     
        while(condition){
        System.out.println("===============================");
              System.out.println(" BANKING SYSTEM ");
              System.out.println("===============================");
              System.out.println("1. Register a customer");
              System.out.println("2. Update  customer ");
              System.out.println("3. Delete customer");
              System.out.println("4.Search customer by ID");
              System.out.println("5. Display  ALL CUSTOMERS");
              System.out.println("--------------------------------");
              System.out.println("Choose: ");
                int choice=sc.nextInt();
                
                String answer="";
                Customer customerObj= new Customer();
                CustomerDao dao = new CustomerDao();
                switch (choice){
                    case 1:
                         System.out.println("Enter your name");
                         names=sc.next();
                          System.out.println("Enter your ID");
                         id=sc.next();
                          System.out.println("Enter your phine number");
                         phoneNumber=sc.next();
                          System.out.println("Enter your account number");
                         accountNumber=sc.next();
                          System.out.println("Enter your age");
                         age=sc.nextInt();
                         
                         customerObj.setNid(id);
                        customerObj.setAccount_number(accountNumber);
                        customerObj. setAge(age);
                         customerObj.setNames(names);
                         customerObj. setPhone_number(phoneNumber);
                         
                         
                         int rowsAffected = dao.createCustomer(customerObj);
                         if(rowsAffected>0){
                         System.out.println("Data inserted");
                                 }
                         else{
                             System.out.println("Data not inserted");
                         }
                         
                         // System.out.println("Wrong choice");
                          System.out.println("Enter Yes to continue or no ");
                          answer=sc.next();
                          if(answer.equalsIgnoreCase("Yes")){
                          condition=true;}
                          else{
                              System.out.println("Thank you for using ");
                              condition=false;
                          }
                       
                        break;


                                         case 2:
    System.out.println("Enter the ID of the customer you want to update:");
    id = sc.next(); // Get the ID of the customer to update

    // Create a new Customer object
    customerObj = dao.getCustomerById(id); // You will create this method to fetch a customer by ID
    if (customerObj != null) {
        System.out.println("Enter new name (Leave blank to keep the current name):");
        sc.nextLine(); 
        String newName = sc.nextLine();
        if (!newName.isEmpty()) customerObj.setNames(newName);

        System.out.println("Enter new age (Leave blank to keep the current age):");
        String ageInput = sc.nextLine();
        if (!ageInput.isEmpty()) customerObj.setAge(Integer.parseInt(ageInput));

        System.out.println("Enter new phone number (Leave blank to keep the current phone number):");
        String newPhone = sc.nextLine();
        if (!newPhone.isEmpty()) customerObj.setPhone_number(newPhone);

        System.out.println("Enter new account number (Leave blank to keep the current account number):");
        String newAccount = sc.nextLine();
        if (!newAccount.isEmpty()) customerObj.setAccount_number(newAccount);

        int rowsAffectedInUpdate = dao.updateCustomer(customerObj);
        if (rowsAffectedInUpdate > 0) {
            System.out.println("Customer updated successfully!");
        } else {
            System.out.println("Customer update failed.");
        }
    } else {
        System.out.println("Customer not found.");
    }
    break;





                    case 3:
    System.out.println("Enter the ID of the customer to delete: ");
    String deleteId = sc.next();
    
    int deleteResult = dao.deleteCustomer(deleteId);
    if (deleteResult > 0) {
        System.out.println("Customer deleted successfully.");
    } else {
        System.out.println("Customer not found.");
    }
    break;




             
    case 4:  // Assuming case 4 is for searching
    System.out.println("Enter the ID of the customer to search: ");
    String searchId = sc.next();
    
    Customer foundCustomer = dao.searchCustomer(searchId);
    if (foundCustomer != null) {
        System.out.println("Customer Details:");
        System.out.println("ID: " + foundCustomer.getNid());
        System.out.println("Name: " + foundCustomer.getNames());
        System.out.println("Age: " + foundCustomer.getAge());
        System.out.println("Phone Number: " + foundCustomer.getPhone_number());
        System.out.println("Account Number: " + foundCustomer.getAccount_number());
    } else {
        System.out.println("Customer not found.");
    }
    break;



                        
                }