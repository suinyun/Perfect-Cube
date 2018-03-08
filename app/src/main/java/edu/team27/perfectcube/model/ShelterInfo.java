package edu.team27.perfectcube.model;

/**
 * Created by Brooklyn on 2/28/2018.
 */

public class ShelterInfo {

    private String shelterName;
    private String capacity;
    private String gender;
    private String address;
    private String phoneNumber;

    public ShelterInfo(String shelterName, String capacity, String gender, String
                       address, String phoneNumber) {
        this.shelterName = shelterName;
        this.capacity = capacity;
        this.gender = gender;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }

    public String toString() {
        return this.shelterName;
    }

    public String getShelterName() {
        return this.shelterName;
    }

    public String getCapacity() {
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
