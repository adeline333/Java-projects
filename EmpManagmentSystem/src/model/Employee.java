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
public class Employee {
    
    private String empId;
    private String fullName;
    private String gender;
    private String email;
    private String phone;
    private String departmentName;

    // Constructors, getters, and setters
    public Employee(String empId, String fullName, String gender, String email, String phone, String departmentName) {
        this.empId = empId;
        this.fullName = fullName;
        this.gender = gender;
        this.email = email;
        this.phone = phone;
        this.departmentName = departmentName;
    }

    public Employee() {
    }

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

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    


    
    
}
