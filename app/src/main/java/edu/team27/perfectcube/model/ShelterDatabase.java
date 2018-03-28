package edu.team27.perfectcube.model;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

/**
 * Created by Brooklyn on 3/27/2018.
 */

@Database(entities = {ShelterInfo.class}, version = 1, exportSchema = false)
public abstract class ShelterDatabase extends RoomDatabase {

    public static ShelterDatabase INSTANCE;

    public static ShelterDatabase getDatabase(Context context) {
        if (INSTANCE == null) {
            Room.databaseBuilder(context.getApplicationContext(),
                    ShelterDatabase.class, "shelter_db").build();
        }
        return INSTANCE;
    }


    public abstract ShelterDao shelterDao();
}