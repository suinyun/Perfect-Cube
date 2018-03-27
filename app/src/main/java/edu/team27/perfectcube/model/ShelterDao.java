package edu.team27.perfectcube.model;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

/**
 * Created by Brooklyn on 3/27/2018.
 */

@Dao
public interface ShelterDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insertShelters(ShelterInfo ... shelters);

    @Update
    public void updateShelters(ShelterInfo ... shelters);

    @Delete
    public void deleteShelters(ShelterInfo ... shelters);

    @Query("SELECT * FROM shelterinfo")
    public ShelterInfo[] loadShelterInfo();
}
