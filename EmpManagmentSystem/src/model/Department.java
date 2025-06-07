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

public class Department {
    private String deptCode;
    private String deptName;
    private String headOfDept;
    private int totalEmployees;
    private int establishedYear;

    public Department(String deptCode, String deptName, String headOfDept, int totalEmployees, int establishedYear) {
        this.deptCode = deptCode;
        this.deptName = deptName;
        this.headOfDept = headOfDept;
        this.totalEmployees = totalEmployees;
        this.establishedYear = establishedYear;
    }

    // Getters and Setters
    public String getDeptCode() {
        return deptCode;
    }

    public void setDeptCode(String deptCode) {
        this.deptCode = deptCode;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getHeadOfDept() {
        return headOfDept;
    }

    public void setHeadOfDept(String headOfDept) {
        this.headOfDept = headOfDept;
    }

    public int getTotalEmployees() {
        return totalEmployees;
    }

    public void setTotalEmployees(int totalEmployees) {
        this.totalEmployees = totalEmployees;
    }

    public int getEstablishedYear() {
        return establishedYear;
    }

    public void setEstablishedYear(int establishedYear) {
        this.establishedYear = establishedYear;
    }
}
