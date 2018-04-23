package edu.team27.perfectCube.model;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

/**
 * part of the room thing for user database
 * */
@Dao
public interface UserDao {

    /**
     * @param users to be added to database
     * */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertUsers(User... users);

    /**
     * @param users whose info needs to be updated
     * */
    @Update
    void updateUsers(User... users);

    /**
     * @param users who need to be deleted from database
     * */
    @Delete
    void deleteUsers(User... users);

    /**
     * @return array of all users in database
     * */
    @Query("SELECT * FROM user")
    User[] loadAllUsers();
}
