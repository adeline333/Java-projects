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


import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;
import model.Role;

public interface RoleInterface extends Remote {
    public String registerRole(Role role) throws RemoteException;
    public String updateRole(Role role) throws RemoteException;
    public String deleteRole(Role role) throws RemoteException;
    public List<Role> getAllRoles() throws RemoteException;
    public Role getRoleById(String roleId) throws RemoteException;
}
