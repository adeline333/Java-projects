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
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import dao.RoleDAO;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;
import model.Role;
import service.RoleInterface;

public class RoleImpl extends UnicastRemoteObject implements RoleInterface {

    RoleDAO dao = new RoleDAO();

    public RoleImpl() throws RemoteException {
        super();
    }

    @Override
    public String registerRole(Role role) throws RemoteException {
        return dao.addRole(role);
    }

    @Override
    public String updateRole(Role role) throws RemoteException {
        return dao.updateRole(role);
    }

    @Override
    public String deleteRole(Role role) throws RemoteException {
        return dao.deleteRole(role);
    }

    @Override
    public List<Role> getAllRoles() throws RemoteException {
        return dao.getAllRoles();
    }

    @Override
    public Role getRoleById(String roleId) throws RemoteException {
        return dao.getRoleById(roleId);
    }
}
