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

@Entity
@Table(name = "departments")
public class Department implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "dept_code")
    private String deptCode;

    @Column(name = "dept_name")
    private String deptName;

    @Column(name = "head_of_dept")
    private String headOfDept;

    @Column(name = "total_employees")
    private int totalEmployees;

    @Column(name = "established_year")
    private int establishedYear;

    @OneToMany(mappedBy = "department", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Employee> employees;

    public Department() {}

    public Department(String deptCode, String deptName, String headOfDept, int totalEmployees, int establishedYear) {
        this.deptCode = deptCode;
        this.deptName = deptName;
        this.headOfDept = headOfDept;
        this.totalEmployees = totalEmployees;
        this.establishedYear = establishedYear;
    }

    // Getters and setters
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

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }
}
