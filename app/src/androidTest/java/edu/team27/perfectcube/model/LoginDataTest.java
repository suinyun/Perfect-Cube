package edu.team27.perfectcube.model;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by suinyun on 4/14/18.
 */
public class LoginDataTest {
    @Test
    public void testFindUser() throws Exception {
        LoginData.addUser("Suin", "Yun", UserType.ADMINISTRATOR, 7, "palace");
        LoginData.addUser("Emma", "Dokansky", UserType.ADMINISTRATOR, 3, "palace");
        LoginData.addUser("Su", "Y", UserType.USER, 9, "place");
        LoginData.addUser("Em", "Dok", UserType.USER, 5, "place");
        assertEquals(true, LoginData.findUser("Suin"));
        assertEquals(true, LoginData.findUser("Em"));
        assertEquals(false, LoginData.findUser("Robert"));
        assertEquals(false, LoginData.findUser(null));

    }
}