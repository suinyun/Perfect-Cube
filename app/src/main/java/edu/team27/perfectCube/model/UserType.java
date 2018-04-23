package edu.team27.perfectCube.model;

/**
 * enum for types of users
 * */
public enum UserType {
    ADMINISTRATOR("Admin"),
    USER("User");

    private final String userType;

    UserType(String userType) {this.userType = userType;}

    public String toString() {return userType;}

}
