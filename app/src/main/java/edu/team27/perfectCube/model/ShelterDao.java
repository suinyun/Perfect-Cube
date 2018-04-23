package edu.team27.perfectCube.model;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

/**
 * part of the room thing for database of shelters
 *
 * */
@Dao
public interface ShelterDao {

    /**
     * adds shelters to database
     *
     * @param shelters a list of shelters to be added
     * */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertShelters(ShelterInfo... shelters);

    /**
     * updates current shelters in database
     *
     * @param shelters the shelters to be updated
     * */
    @Update
    void updateShelters(ShelterInfo... shelters);

    /**
     * deletes shelters from database
     *
     * @param shelters the shelters to be deleted
     * */
    @Delete
    void deleteShelters(ShelterInfo... shelters);

    /**
     * who knows!
     * @return an array of shelters
     * */
    @Query("SELECT * FROM shelterinfo")
    ShelterInfo[] loadAllShelters();
}
