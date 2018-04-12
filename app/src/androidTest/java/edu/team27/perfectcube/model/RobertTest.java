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

    private static User user1;
    private static User user2;
    private static User user3;
    private static User user4;
    private static User user5;

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

    @Before
    public void createUsers() {
        user1 = new User("Jimmy", "password", UserType.USER, 1, "Here");
        user2 = new User("Jimmy", "password", UserType.USER, 1, "Here");
        user3 = new User("Taylor", "password", UserType.USER, 17, "Here");;
        user4 = new User("Amara", "12345", UserType.USER, 23, "There");
        user5 = new User("Christine", "openSesame", UserType.USER, 93, "Southwest");
    }

    @Test
    public void getPass() throws Exception {
        assertEquals("whatever", LoginData.getPass("idunno"));
    }

    @Test
    public void testUsers() {
        assertTrue(user1.equals(user2));
        assertFalse(user1.equals(user3));
        assertFalse(user2.equals(user4));
        assertFalse(user2.equals(user5));
        assertTrue(!user1.getUsername().equals(user4.getUsername()));
        assertFalse(user2.getUsername().equals(user3.getUsername()));
        assertTrue(user1.getPassword().equals(user3.getPassword()));
        assertFalse(user2.getPassword().equals(user4.getPassword()));
        assertTrue(user3.getReservationNumber() != user5.getReservationNumber());
        assertFalse(user4.getReservationNumber() == user5.getReservationNumber());
        assertTrue(user1.getReservationLocation().equals(user3.getReservationLocation()));
        assertFalse(user2.getReservationLocation().equals(user5.getReservationLocation()));
    }

    @After
    public void closeDb() throws IOException {
        mDb.close();
    }
}