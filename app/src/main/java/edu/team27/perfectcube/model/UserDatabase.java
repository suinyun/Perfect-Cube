package edu.team27.perfectcube.model;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;
import android.content.Context;

/**
 * Created by Brooklyn on 3/27/2018.
 */

@Database(entities = {User.class}, version = 1, exportSchema = false)
@TypeConverters({UserTypeConverter.class})
public abstract class UserDatabase extends RoomDatabase{

    public static UserDatabase INSTANCE;

    /*public static UserDatabase getDatabase(Context context) {
        if (INSTANCE == null) {
            Room.databaseBuilder(context.getApplicationContext(),
                    UserDatabase.class, "user_db").build();
        }
        return INSTANCE;
    }*/

    public abstract UserDao userDao();
}
