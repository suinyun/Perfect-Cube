package edu.team27.perfectCube.model;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

/**
 * creates the shelter database i think
 *
 * */
@Database(entities = {ShelterInfo.class}, version = 1, exportSchema = false)
public abstract class ShelterDatabase extends RoomDatabase {

    static ShelterDatabase INSTANCE;

    /**
     * calls the dao guy
     *
     * @return the dao guy
     * */
    public abstract ShelterDao shelterDao();
}
