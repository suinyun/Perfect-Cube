package edu.team27.perfectcube.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

/**
 * Created by Brooklyn on 2/28/2018.
 */

@Entity
public class ShelterInfo {

    @PrimaryKey
    @NonNull
    private String shelterName;
    @ColumnInfo
    private String capacity;
    @ColumnInfo
    private String gender;
    @ColumnInfo
    private String address;
    @ColumnInfo
    private String phoneNumber;
    @ColumnInfo
    private double longitude;
    @ColumnInfo
    private double latitude;

    @Ignore
    public ShelterInfo() {

    }

    public ShelterInfo(String shelterName, String capacity, String gender,
                       double longitude, double latitude, String address,
                       String phoneNumber) {
        this.shelterName = shelterName;
        this.capacity = capacity;
        this.gender = gender;
        this.longitude = longitude; //Keep an eye out for this.
        this.latitude = latitude; //May cause issues if negative
        this.address = address;
        this.phoneNumber = phoneNumber;
    }

    public String toString() {
        return this.shelterName;
    }

    public String getShelterName() {
        return this.shelterName;
    }

    public void setShelterName(String shelterName) {this.shelterName = shelterName; }

    public String getCapacity() {
        return this.capacity;
    }

    public void setCapacity(String cap) { capacity = cap; }

    public String getGender() {
        return this.gender;
    }

    public void setGender(String gender) {this.gender = gender; }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {this.address = address; }

    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {this.phoneNumber = phoneNumber; }

    public double getLongitude() { return this.longitude; }

    public void setLongitude(Double longitude) {this.longitude = longitude; }

    public double getLatitude() { return this.latitude; }

    public void setLatitude(Double latitude) {this.latitude = latitude; }

    public static boolean needsToRead() { return true; } //Change to true, build and run if ShelterDatabase gets out of whack.
}