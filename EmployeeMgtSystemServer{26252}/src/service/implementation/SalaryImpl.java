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



import dao.SalaryDAO;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;
import model.Salary;
import service.SalaryInterface;

public class SalaryImpl extends UnicastRemoteObject implements SalaryInterface {

    public SalaryImpl() throws RemoteException {
        super();
    }

    SalaryDAO dao = new SalaryDAO();

    @Override
    public String registerSalary(Salary salary) throws RemoteException {
        return dao.registerSalary(salary);
    }

    @Override
    public String updateSalary(Salary salary) throws RemoteException {
        return dao.updateSalary(salary);
    }

    @Override
    public String deleteSalary(Salary salary) throws RemoteException {
        return dao.deleteSalary(salary);
    }

    @Override
    public List<Salary> retrieveAllSalaries() throws RemoteException {
        return dao. retrieveAllSalaries();
    }

    @Override
    public Salary retrieveSalaryById(String salaryCode) throws RemoteException {
        return dao.retrieveSalaryById( salaryCode);
    }
    
    
 
    
}




