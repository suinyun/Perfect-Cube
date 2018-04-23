package edu.team27.perfectCube.model;

import android.arch.persistence.room.TypeConverter;


class UserTypeConverter {

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
        if ("".equals(string)) {
            return null;
        } else {
            if ("admin".equalsIgnoreCase(string)) {
                return UserType.ADMINISTRATOR;
            } else {
                return UserType.USER;
            }
        }
    }
}


