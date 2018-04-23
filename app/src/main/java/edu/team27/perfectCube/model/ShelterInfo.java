package edu.team27.perfectCube.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

/**
 * stores/assigns all info about all shelters
 *
 * */
@Entity
public class ShelterInfo {

    @PrimaryKey
    @NonNull
    private final String shelterName;
    @ColumnInfo
    private String capacity;
    @ColumnInfo
    private final String gender;
    @ColumnInfo
    private final String address;
    @ColumnInfo
    private final String phoneNumber;

    private final double latitude;
    private final double longitude;

    /**
     * creates a ShelterInfo object
     *
     * @param shelterName the name of the shelter
     * @param capacity number of vacancies
     * @param gender the demographic rules
     * @param longitude of shelter
     * @param latitude of shelter
     * @param address address of shelter
     * @param phoneNumber the phone number of the shelter
     * */
    public ShelterInfo(@NonNull String shelterName, String capacity, String gender,
                       double longitude, double latitude, String address,
                       String phoneNumber) {
        this.shelterName = shelterName;
        this.capacity = capacity;
        this.gender = gender;
        this.latitude = latitude;
        this.longitude = longitude;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }

    public String toString() {
        return this.shelterName;
    }

    /**
     *
     * @return the name of the shelter
     * */
    @NonNull
    public String getShelterName() {
        return this.shelterName;
    }

    /**
     *
     * @return number of shelter vacancies
     * */
    public String getCapacity() {
        return this.capacity;
    }

    /**
     *
     * @param cap the number of vacancies
     * */
    public void setCapacity(String cap) { capacity = cap; }

    /**
     *
     * @return demographic restrictions of the shelter
     * */
    public String getGender() {
        return this.gender;
    }

    /**
     *
     * @return shelter's latitude
     * */
    public double getLatitude() { return  this.latitude; }

    /**
     *
     * @return shelter's longitude
     * */
    public double getLongitude() { return this.longitude; }

    /**
     *
     * @return shelter's address
     * */
    public String getAddress() {
        return this.address;
    }

    /**
     *
     * @return shelter's phone number
     * */
    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    /**
     * Change to true, build and run if ShelterDatabase gets out of whack.
     *
     * @return if csv reader needs to be reread
     * */
    public static boolean needsToRead() { return true; }

}
