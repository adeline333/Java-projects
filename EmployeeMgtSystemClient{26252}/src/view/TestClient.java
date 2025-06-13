/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

/**
 *
 * @author adeli
 */
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import controller.DepartmentClientController;
import java.util.List;

/**
 *
 * @author adeli
 */
public class TestClient {
    public static void main(String[] args) {
        try {
            DepartmentClientController deptController = new DepartmentClientController();
            
            // Example usage
            List<model.Department> depts = deptController.getAllDepartments();
            for (model.Department dept : depts) {
                System.out.println(dept.getDeptName());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

