package edu.team27.perfectcube.model;

import android.content.Context;

import java.util.*;

/**
 * Created by Brooklyn on 2/13/2018.
 */

public class LoginData {
    private static LoginData ourInstance = new LoginData();
    private static ArrayList<User> userList;
    private static UserDatabase ub;
    private static Context context;

    public LoginData() {

    }

    //private static ArrayList<String> users = new ArrayList<>();
    //private static ArrayList<String> passwords = new ArrayList<>();
    public static void addUser(User user) {userList.add(user);}
    //public static void addPass(String pass) {users.add(pass);}

    /*
    public void initialize() {
        addUser("user");
        addPass("pass");
    } */

    public static void setContext(Context newContext) {
        context = newContext;
        ub = UserDatabase.getDatabase(context);
        userList = new ArrayList<>(Arrays.asList(ub.userDao().loadAllUsers()));
    }

    public static ArrayList<User> getUsers() {
        return userList;
    }

    public static void setUsers(ArrayList<User> users) {
        LoginData.userList = users;
    }

    /*public static ArrayList<String> getPasswords() {
        return passwords;
    }

    public static void setPasswords(ArrayList<String> passwords) {
        LoginData.passwords = passwords;
    }*/


    static LoginData getInstance() {
        return ourInstance;
    }


    /*public String getUser() {
        return myUsername;
    }*/


}
