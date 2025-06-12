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
import javax.persistence.*;

@Entity
@Table(name = "salaries")
public class Salary implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "salary_code")
    private String salaryCode;

    @Column(name = "basic_salary")
    private double basicSalary;

    @Column(name = "bonus")
    private double bonus;

    @Column(name = "deductions")
    private double deductions;

    @Column(name = "pay_month")
    private String payMonth;

    @ManyToOne
    @JoinColumn(name = "emp_id")
    private Employee employee;

    public Salary() {}

    public Salary(String salaryCode, double basicSalary, double bonus, double deductions, String payMonth) {
        this.salaryCode = salaryCode;
        this.basicSalary = basicSalary;
        this.bonus = bonus;
        this.deductions = deductions;
        this.payMonth = payMonth;
    }

    // Getters and setters
    public String getSalaryCode() {
        return salaryCode;
    }

    public void setSalaryCode(String salaryCode) {
        this.salaryCode = salaryCode;
    }

    public double getBasicSalary() {
        return basicSalary;
    }

    public void setBasicSalary(double basicSalary) {
        this.basicSalary = basicSalary;
    }

    public double getBonus() {
        return bonus;
    }

    public void setBonus(double bonus) {
        this.bonus = bonus;
    }

    public double getDeductions() {
        return deductions;
    }

    public void setDeductions(double deductions) {
        this.deductions = deductions;
    }

    public String getPayMonth() {
        return payMonth;
    }

    public void setPayMonth(String payMonth) {
        this.payMonth = payMonth;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
}
