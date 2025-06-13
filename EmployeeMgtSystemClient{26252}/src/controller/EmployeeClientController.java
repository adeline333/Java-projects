/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

/**
 *
 * @author adeli
 */

import java.rmi.RemoteException;
import java.util.List;
import model.Employee;
import service.EmployeeInterface;

public class EmployeeClientController {
    private final EmployeeInterface service = ClientConnector.getEmployeeService();

    public String registerEmployee(Employee emp) throws RemoteException {
        return service.registerEmployee(emp);
    }

    public String updateEmployee(Employee emp) throws RemoteException {
        return service.updateEmployee(emp);
    }

    public String deleteEmployee(Employee emp) throws RemoteException {
        return service.deleteEmployee(emp);
    }

    public List<Employee> getAllEmployees() throws RemoteException {
        return service.retrieveAllEmployees();
    }

   public Employee retrieveEmployeeById(String empId) throws RemoteException {
    return service.retrieveEmployeeById(empId);
}
 
   
   
}
