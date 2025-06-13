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


import java.io.Serializable;
import java.util.List;
import javax.persistence.*;


public class Employee implements Serializable {
     private static final long serialVersionUID = 1L;
   
    private String empId;


    private String fullName;

  
    private String gender;


    private String email;

    private String phone;

private Department department;




    private List<Salary> salaries;

    public Employee() {}

    public Employee(String empId, String fullName, String gender, String email, String phone) {
        this.empId = empId;
        this.fullName = fullName;
        this.gender = gender;
        this.email = email;
        this.phone = phone;
    }

    // Getters and Setters
    public String getEmpId() {
        return empId;
    }

    public void setEmpId(String empId) {
        this.empId = empId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public List<Salary> getSalaries() {
        return salaries;
    }

    public void setSalaries(List<Salary> salaries) {
        this.salaries = salaries;
    }
}
