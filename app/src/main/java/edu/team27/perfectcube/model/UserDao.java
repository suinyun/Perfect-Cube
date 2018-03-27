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
public interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insertUsers(User ... users);

    @Update
    public void updateUsers(User ... users);

    @Delete
    public void deleteUsers(User ... users);

    @Query("SELECT * FROM user")
    public User[] loadAllUsers();
}
