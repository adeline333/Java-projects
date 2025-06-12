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
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import service.implementation.DepartmentImpl;
import service.implementation.EmployeeImpl;
import service.implementation.SalaryImpl;
import service.implementation.UserImpl;
import service.implementation.RoleImpl;

public class Server {
    private DepartmentImpl departmentImpl;
    private EmployeeImpl employeeImpl;
    private SalaryImpl salaryImpl;
    private UserImpl userImpl;
    private RoleImpl roleImpl;

    public Server() throws RemoteException {
        this.departmentImpl = new DepartmentImpl();
        this.employeeImpl = new EmployeeImpl();
        this.salaryImpl = new SalaryImpl();
        this.userImpl = new UserImpl();
        this.roleImpl = new RoleImpl();
    }

    public static void main(String[] args) {
        try {
            System.setProperty("java.rmi.server.hostname", "127.0.0.1");
            Registry registry = LocateRegistry.createRegistry(5000);

            Server server = new Server();

            registry.rebind("department", server.departmentImpl);
            registry.rebind("employee", server.employeeImpl);
            registry.rebind("salary", server.salaryImpl);
            registry.rebind("user", server.userImpl);
            registry.rebind("role", server.roleImpl);

            System.out.println("Server is running on port 5000...");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
