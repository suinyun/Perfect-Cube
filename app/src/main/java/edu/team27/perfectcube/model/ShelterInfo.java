package edu.team27.perfectcube.model;

/**
 * Created by Brooklyn on 2/28/2018.
 */

public class ShelterInfo {

    private String shelterName;
    private int capacity;
    private String gender;
    private String address;
    private String phoneNumber;

    public ShelterInfo(String shelterName, int capacity, String gender, String
                       address, String phoneNumber) {
        this.shelterName = shelterName;
        this.capacity = capacity;
        this.gender = gender;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }

    public String toString() {
        return this.shelterName + "(" + this.phoneNumber + ")";
    }

    public String getShelterName() {
        return this.shelterName;
    }

    public int getCapacity() {
        return this.capacity;
    }

    public String getGender() {
        return this.gender;
    }

    public String getAddress() {
        return this.address;
    }

    public String getPhoneNumber() {
        return this.phoneNumber;
    }
}
