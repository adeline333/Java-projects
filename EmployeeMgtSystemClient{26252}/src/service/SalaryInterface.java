/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

/**
 *
 * @author adeli
 */


import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;
import model.Salary;

public interface SalaryInterface extends Remote {
    public String registerSalary(Salary salary) throws RemoteException;
    public String updateSalary(Salary salary) throws RemoteException;
    public String deleteSalary(Salary salary) throws RemoteException;
    public List<Salary> retrieveAllSalaries() throws RemoteException;
    public Salary retrieveSalaryById(String salaryCode) throws RemoteException;
}

