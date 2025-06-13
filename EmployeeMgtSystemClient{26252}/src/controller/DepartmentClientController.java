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
import model.Department;
import service.DepartmentInterface;

public class DepartmentClientController {
    private final DepartmentInterface service = ClientConnector.getDepartmentService();

    public String registerDepartment(Department dept) throws RemoteException {
        return service.registerDepartment(dept);
    }

    public String updateDepartment(Department dept) throws RemoteException {
        return service.updateDepartment(dept);
    }

    public String deleteDepartment(Department dept) throws RemoteException {
        return service.deleteDepartment(dept);
    }

    public List<Department> getAllDepartments() throws RemoteException {
        return service.getAllDepartments();
    }

    public Department getDepartmentByCode(String code) throws RemoteException {
        return service.getDepartmentByCode(code);
    }
}
