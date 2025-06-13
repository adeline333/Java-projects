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


// DepartmentInterface.java


import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;
import model.Department;

public interface DepartmentInterface extends Remote {
    public String registerDepartment(Department department) throws RemoteException;
    public String updateDepartment(Department department) throws RemoteException;
    public String deleteDepartment(Department department) throws RemoteException;

    // Retrieve all departments
    public List<Department> getAllDepartments() throws RemoteException;

    // Retrieve department by code
    public Department getDepartmentByCode(String deptCode) throws RemoteException;
}
