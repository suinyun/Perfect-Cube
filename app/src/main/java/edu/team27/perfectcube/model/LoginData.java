package edu.team27.perfectcube.model;

import java.util.*;

/**
 * Created by Brooklyn on 2/13/2018.
 */

public class LoginData {
    //private static final LoginData ourInstance = new LoginData();
    private String myUsername;

    public LoginData(String user) {

        myUsername = user;
    }

    private static ArrayList<String> users = new ArrayList<>();
    private static ArrayList<String> passwords = new ArrayList<>();
    public static void addUser(String user) {users.add(user);}
    public static void addPass(String pass) {users.add(pass);}

    /*
    public void initialize() {
        addUser("user");
        addPass("pass");
    } */

    public static ArrayList<String> getUsers() {
        return users;
    }

    public static void setUsers(ArrayList<String> users) {
        LoginData.users = users;
    }

    public static ArrayList<String> getPasswords() {
        return passwords;
    }

    public static void setPasswords(ArrayList<String> passwords) {
        LoginData.passwords = passwords;
    }

    /*
    static LoginData getInstance() {
        return ourInstance;
    } */


    public String getUser() {
        return myUsername;
    }
}
