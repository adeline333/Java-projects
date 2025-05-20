/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author adeli
 */
public class Customer {
    

     private String nid;
    private String names;
    private int age;
    private String phone_number;
    private String account_number;

    public Customer() {
    }

    
    
    public Customer(String nid, String names, int age, String phone_number, String account_number) {
        this.nid = nid;
        this.names = names;
        this.age = age;
        this.phone_number = phone_number;
        this.account_number = account_number;
    }

    public String getNid() {
        return nid;
    }

    public void setNid(String nid) {
        this.nid = nid;
    }

    public String getNames() {
        return names;
    }

    public void setNames(String names) {
        this.names = names;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getAccount_number() {
        return account_number;
    }

    public void setAccount_number(String account_number) {
        this.account_number = account_number;
    }

    
}
