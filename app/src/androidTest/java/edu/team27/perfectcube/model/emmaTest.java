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


import static edu.team27.perfectcube.model.LoginData.getUser;
import static org.junit.Assert.*;


@RunWith(AndroidJUnit4.class)
public class emmaTest {
    private UserDao eUserDao;
    private UserDatabase eDb;
    private static ArrayList<User> userInfo = new ArrayList<>();

    private static User user1;
    private static User user2;
    private static User user3;
    private static User user4;
    private static User user5;

    @Before
    public void createDb() {
        Context context = InstrumentationRegistry.getTargetContext();
        eDb = Room.databaseBuilder(context,
                UserDatabase.class, "user-database").allowMainThreadQueries()
                .build();
        eUserDao = eDb.getUserDao();
        userInfo.addAll(Arrays.asList(eUserDao.loadAllUsers()));
        LoginData.setUserInfo(userInfo);
    }

    @Before
    public void createUsers() {
        user1 = new User("Rachel", "Green", UserType.USER, 1, "New York");
    }

    @Test
    public void testUsers() {
        User one = getUser("Rachel");
        assertEquals(one, userInfo.get(2));
        User two = getUser("emma");
        assertEquals(two.getUsername(), null);
        assertEquals(two.getPassword(), null);
        assertEquals(two.getReservationNumber(), 0);
        assertEquals(two.getReservationLocation(), null);
    }

    @After
    public void closeDb() throws IOException {
        eDb.close();
    }
}