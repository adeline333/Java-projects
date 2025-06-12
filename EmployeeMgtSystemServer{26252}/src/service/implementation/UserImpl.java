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


import dao.UserDAO;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;
import model.User;
import service.UserInterface;

public class UserImpl extends UnicastRemoteObject implements UserInterface {

    UserDAO dao = new UserDAO();

    public UserImpl() throws RemoteException {
        super();
    }

    @Override
    public String registerUser(User user) throws RemoteException {
        return dao.addUser(user);
    }

    @Override
    public String updateUser(User user) throws RemoteException {
        return dao.updateUser(user);
    }

    @Override
    public String deleteUser(User user) throws RemoteException {
        return dao.deleteUser(user);
    }

    @Override
    public List<User> getAllUsers() throws RemoteException {
        return dao.getAllUsers();
    }

    @Override
    public User getUserByUsername(String username) throws RemoteException {
        return dao.getUserByUsername(username);
    }
}
