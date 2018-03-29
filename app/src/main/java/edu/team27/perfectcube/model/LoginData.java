package edu.team27.perfectcube.model;

import java.util.*;

import android.content.Context;

/**
 * Created by Brooklyn on 2/13/2018.
 */

public class LoginData {
    private static final LoginData ourInstance = new LoginData();
    public static UserDatabase db; //Brooklyn wrote this variable as "ub," but I ended up using "db" everywhere.
    //private String myUsername;
    private static ArrayList<User> userInfo = new ArrayList<>();
    private static User first = new User("user","pass",UserType.USER,0,"");

    //private Context context; WARNING!!!! Never store Contexts. They were not meant to be stored. Leave this line commented.

    public LoginData() {

    }

    //public static void setContext(Context newContext) {
        //context = newContext;
        //db = UserDatabase.getDatabase(newContext);
        //if (db == null) { //db is null when I start it up because the database is null and I have no idea how to write to either without unintended consequences.
            //User first = new User("user","pass",UserType.USER);
            //UserDatabase.userDao().insertUsers(first);
            /*the userDao method won't work when setContext is static, but WelcomeActivity can't call setContext
              when setContext isn't static. Unfortunately, I have no idea why WelcomeActivity feels it needs
              static context. Figuring out why WelcomeActivity wants this to be static would fix everything,
              but I couldn't figure it out.
             */
        //} else {
            //userInfo = new ArrayList<>(Arrays.asList(db.userDao().loadAllUsers())); //I haven't gotten to the point where this line will run, so hopefully it will work.
        //}
    //}

    public static boolean findUser(String name) {
        userInfo.add(first);
        int length = userInfo.size();
        for (int i = 0; i < length; i++) {
            if (userInfo.get(i).getUsername().equals(name)) {
                return true;
            }
        }
        return false;
    }

    public static String getPass(String name) {
        int length = userInfo.size();
        String pass = "";
        for (int i = 0; i < length; i++) {
            if (userInfo.get(i).getUsername().equals(name)) {
                pass = userInfo.get(i).getPassword();
            }
        }
        return pass;
    }

    public static User getUser(String name) {
        User ourUser = new User();
        int length = userInfo.size();
        for (int i = 0; i < length; i++) {
            if (userInfo.get(i).getUsername().equals(name)) {
                ourUser = userInfo.get(i);
            }
        }
        return ourUser;
    }

    public static void addUser(String name, String pass, UserType type, int number, String place) {
        User newser = new User(name,pass,type,number,place);
        userInfo.add(newser);
        /*I've been storing registered users in this userInfo ArrayList, which isn't linked to the database, but is
          easier to work with. I can't write to db, because doing so requires using an interface method call on a
          null object, and I can't write to the UserDatabase itself because of a catch-22 wherein the method to do
          it must be simultaneously static and non-static.
        */
    }

    /*
    static LoginData getInstance() {
        return ourInstance;
    } */

    /*
    public String getUser() {
        return myUsername;
    }
    */
}
