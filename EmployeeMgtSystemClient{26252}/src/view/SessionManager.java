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
public class SessionManager {
    private static String currentUserEmail = null;

    public static void login(String email) {
        currentUserEmail = email;
    }

    public static void logout() {
        
        
            System.out.println("Logging out..."); // Debug
    currentUserEmail = null;
    System.out.println("Current user is now: " +  currentUserEmail); 
    }

    public static boolean isLoggedIn() {
        return currentUserEmail != null;
    }

    public static String getCurrentUser() {
        return currentUserEmail;
    }
    
}
