/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service.implementation;

/**
 *
 * @author adeli
 */


import dao.EmployeeDAO;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;
import model.Employee;
import service.EmployeeInterface;
import dao.EmployeeDAO;

public class EmployeeImpl extends UnicastRemoteObject implements EmployeeInterface {

    public EmployeeImpl() throws RemoteException {
        super();
    }

    EmployeeDAO dao = new EmployeeDAO();

    @Override
    public String registerEmployee(Employee employee) throws RemoteException {
        return dao.registerEmployee(employee);
    }

    @Override
    public String updateEmployee(Employee employee) throws RemoteException {
        return dao.updateEmployee(employee);
    }

    @Override
    public String deleteEmployee(Employee employee) throws RemoteException {
        return dao.deleteEmployee(employee);
    }

    @Override
    public List<Employee> retrieveAllEmployees() throws RemoteException {
        return dao.retrieveAllEmployees();
    }

     @Override
       public Employee retrieveEmployeeById(String empId) throws RemoteException {
           return dao.retrieveEmployeeById(empId);
       }
       
       
    

}





