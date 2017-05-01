package cz.tul;

import cz.tul.data.User;
import cz.tul.data.UsersDao;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by The CAT
 */
public class UsersDaoTests {

    @Autowired
    private UsersDao usersDao;

    @Test
    public void testExists() {



        usersDao.create(new User("franta"));
        usersDao.create(new User("honza"));

        assertTrue( usersDao.exists("franta"));
        assertEquals("Should be comment retrieved comments.", 3, usersDao.getAllUsers().size());
        usersDao.deleteUsers();


    }


}
