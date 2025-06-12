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

import dao.DepartmentDAO;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;
import model.Department;
import service.DepartmentInterface;

public class DepartmentImpl extends UnicastRemoteObject implements DepartmentInterface {

    public DepartmentImpl() throws RemoteException {
        super();
    }

    DepartmentDAO dao = new DepartmentDAO();

    @Override
    public String registerDepartment(Department department) throws RemoteException {
        return dao.addDepartment(department);
    }

    @Override
    public String updateDepartment(Department department) throws RemoteException {
        return dao.updateDepartment(department);
    }

    @Override
    public String deleteDepartment(Department department) throws RemoteException {
        return dao.deleteDepartment(department);
    }

    @Override
    public List<Department> getAllDepartments() throws RemoteException {
        return dao.getAllDepartments();
    }

    @Override
    public Department getDepartmentByCode(String deptCode) throws RemoteException {
        return dao.getDepartmentByCode(deptCode);
    }
 
    
}
