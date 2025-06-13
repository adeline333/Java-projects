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
import model.Salary;
import service.SalaryInterface;

public class SalaryClientController {
    private final SalaryInterface service = ClientConnector.getSalaryService();

    public String registerSalary(Salary salary) throws RemoteException {
        return service.registerSalary(salary);
    }

    public String updateSalary(Salary salary) throws RemoteException {
        return service.updateSalary(salary);
    }

    public String deleteSalary(Salary salary) throws RemoteException {
        return service.deleteSalary(salary);
    }

    public List<Salary> retrieveAllSalaries() throws RemoteException {
        return service.retrieveAllSalaries();
    }

    public Salary retrieveSalaryById(String salaryCode) throws RemoteException {
        return service.retrieveSalaryById(salaryCode);
    }
}
