package edu.team27.perfectcube.model;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.*;

/**
 * Created by Robert on 4/9/2018.
 */
@RunWith(AndroidJUnit4.class)
public class RobertTest {
    private UserDao mUserDao;
    private UserDatabase mDb;
    private static ArrayList<User> userInfo = new ArrayList<>();

    @Before
    public void createDb() {
        Context context = InstrumentationRegistry.getTargetContext();
        mDb = Room.databaseBuilder(context,
                UserDatabase.class, "user-database").allowMainThreadQueries()
                .build();
        mUserDao = mDb.getUserDao();
        userInfo.addAll(Arrays.asList(mUserDao.loadAllUsers()));
        LoginData.setUserInfo(userInfo);
    }

    @Test
    public void getPass() throws Exception {
        assertEquals("whatever", LoginData.getPass("idunno"));
    }

    @After
    public void closeDb() throws IOException {
        mDb.close();
    }
}