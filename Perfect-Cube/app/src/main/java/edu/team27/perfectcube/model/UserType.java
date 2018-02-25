package edu.team27.perfectcube.model;

/**
 * Created by suinyun on 2/22/18.
 */

public enum UserType {
    ADMINISTRATOR("Admin"),
    USER("User");

    private String usertype;

    UserType(String usertype) {this.usertype = usertype;}

    public String getUsertype() {
        return usertype;
    }
    public void setUsertype(String usertype) {
        this.usertype = usertype;
    }

    public String toString() {return usertype;}

}
