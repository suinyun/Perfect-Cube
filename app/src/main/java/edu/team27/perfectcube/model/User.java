package edu.team27.perfectcube.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;
import android.support.annotation.NonNull;

/**
 * Created by suinyun on 2/22/18.
 */

@Entity
public class User {
    @PrimaryKey
    @NonNull
    private String username;
    @ColumnInfo
    private String password;
    @ColumnInfo
    @TypeConverters(UserTypeConverter.class)
    private UserType userType;
    @ColumnInfo
    private int reservationNumber;
    @ColumnInfo
    private String reservationLocation;

    public User(String username, String password, UserType userType,
                int reservationNumber, String reservationLocation) {
        this.username = username;
        this.password = password;
        this.userType = userType;
        this.reservationNumber = reservationNumber;
        this.reservationLocation = reservationLocation;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getReservationNumber() {return reservationNumber;}

    public void setReservationNumber(int reservationNumber) {
        this.reservationNumber = reservationNumber;
    }

    public String getReservationLocation() { return reservationLocation;}

    public void setReservationLocation(String reservationLocation) {
        this.reservationLocation = reservationLocation;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (!username.equals(user.username)) return false;
        return password.equals(user.password);
    }

    @Override
    public int hashCode() {
        int result = username.hashCode();
        result = 31 * result + password.hashCode();
        return result;
    }
}
