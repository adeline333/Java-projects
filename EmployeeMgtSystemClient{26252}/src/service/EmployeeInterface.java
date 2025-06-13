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
import model.Employee;


public interface EmployeeInterface extends Remote {
    public String registerEmployee(Employee employee) throws RemoteException;
    public String updateEmployee(Employee employee) throws RemoteException;
    public String deleteEmployee(Employee employee) throws RemoteException;
    public List<Employee> retrieveAllEmployees() throws RemoteException;
    public Employee retrieveEmployeeById(String empId) throws RemoteException;
}
