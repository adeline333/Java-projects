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


import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import service.DepartmentInterface;
import service.EmployeeInterface;
import service.SalaryInterface;

public class ClientConnector {
    private static final String HOST = "127.0.0.1";
    private static final int PORT = 5000;

    private static DepartmentInterface departmentService;
    private static EmployeeInterface employeeService;
    private static SalaryInterface salaryService;

    static {
        try {
            Registry registry = LocateRegistry.getRegistry(HOST, PORT);
            departmentService = (DepartmentInterface) registry.lookup("department");
            employeeService = (EmployeeInterface) registry.lookup("employee");
            salaryService = (SalaryInterface) registry.lookup("salary");

            System.out.println("Client connected to RMI services...");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static DepartmentInterface getDepartmentService() {
        return departmentService;
    }

    public static EmployeeInterface getEmployeeService() {
        return employeeService;
    }

    public static SalaryInterface getSalaryService() {
        return salaryService;
    }
}
