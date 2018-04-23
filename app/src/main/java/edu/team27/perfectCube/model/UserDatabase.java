package edu.team27.perfectCube.model;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;

/**
 * does stuff to create user database
 * */
@Database(entities = {User.class}, version = 1, exportSchema = false)
@TypeConverters({UserTypeConverter.class})
public abstract class UserDatabase extends RoomDatabase{

    static UserDatabase INSTANCE;

    /**
     * @return the dao guy for users
     * */
    public abstract UserDao userDao();
}
