package edu.team27.perfectCube.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;
import android.support.annotation.NonNull;

/**
 * creates and sets stuff for a user
 * */
@Entity
public class User {
    @PrimaryKey
    @NonNull
    private String username = "";
    @ColumnInfo
    private String password;
    @ColumnInfo
    @TypeConverters(UserTypeConverter.class)
    private UserType userType;
    @ColumnInfo
    private int reservationNumber;
    @ColumnInfo
    private String reservationLocation;

    /**
     * creates a user
     * */
    @Ignore
    public User () {
        //Please never use this.
    }

    /**
     * creates a user
     *
     * @param username the users name
     * @param password the users password
     * @param userType admin or user
     * @param reservationNumber how many beds they've reserved
     * @param reservationLocation where they have a reservation
     * */
    public User(@NonNull String username, String password, UserType userType,
                int reservationNumber, String reservationLocation) {
        this.username = username;
        this.password = password;
        this.userType = userType;
        this.reservationNumber = reservationNumber;
        this.reservationLocation = reservationLocation;
    }

    /**
     * @return admin or user
     * */
    public UserType getUserType() {
        return userType;
    }

    /**
     * @return user's name
     * */
    @NonNull
    public String getUsername() {
        return username;
    }

    /**
     * @return user's password
     * */
    public String getPassword() {
        return password;
    }

    /**
     * @return user's reservation bed count
     * */
    public int getReservationNumber() { return reservationNumber;}

    /**
     * @param reservationNumber number of bed's they've reserved
     * */
    public void setReservationNumber(int reservationNumber) {
        this.reservationNumber = reservationNumber;
    }

    /**
     * @return user's reservation location
     * */
    public String getReservationLocation() { return reservationLocation;}

    /**
     * @param reservationLocation where they have reservation
     * */
    public void setReservationLocation(String reservationLocation) {
        this.reservationLocation = reservationLocation;
    }

    /**
     * @param o the object being compared
     * @return if the same or nah
     * */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if ((o == null) || (getClass() != o.getClass())) {
            return false;
        }

        User user = (User) o;

        return username.equals(user.username) && password.equals(user.password);
    }

    /**
     * @return the hashcode
     * */
    @Override
    public int hashCode() {
        int result = username.hashCode();
        result = (31 * result) + password.hashCode();
        return result;
    }
}
