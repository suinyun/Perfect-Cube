package edu.team27.perfectcube.model;

import android.arch.persistence.room.TypeConverter;
import android.arch.persistence.room.TypeConverters;

/**
 * Created by Brooklyn on 3/27/2018.
 */

public class UserTypeConverter {

    @TypeConverter
    public static String enumToString(UserType userType) {
        if (userType == null) {
            return "";
        } else {
            return userType.toString();
        }
    }

    @TypeConverter
    public static UserType stringToEnum(String string) {
        if (string == null) {
            return null;
        } else {
            if (string.equalsIgnoreCase("admin")) {
                return UserType.ADMINISTRATOR;
            } else {
                return UserType.USER;
            }
        }
    }
}


