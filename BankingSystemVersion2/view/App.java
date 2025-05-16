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
                        
                }