package dev.adventure.servicetests;

import dev.adventure.daos.UserDao;
import dev.adventure.daos.UserDaoImpl;
import dev.adventure.entities.User;
import dev.adventure.exceptions.EntityNotFoundException;
import dev.adventure.services.UserService;
import dev.adventure.services.UserServiceImp;
import dev.adventure.utils.Password;
import org.mockito.Mockito;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.List;

public class UserServiceTests {

    UserDao userDao = new UserDaoImpl();
    UserService userService = new UserServiceImp(userDao);
    String [] hashAndSalt = Password.hashGriddle("The Correct Password");
    User testUser = new User(0, "Cleric", "HealsMcGee", "healers4life", hashAndSalt[0], hashAndSalt[1],1);




    @BeforeClass
    void setup(){
        userDao.createUser(testUser);
    }

    @AfterClass
    void tearDown(){
        userDao.deleteUser(testUser.getId());
    }

    @Test
    void testLogin(){
        Assert.assertEquals(userService.login("healers4life", "The Correct Password"), testUser.getId());
    }

    @Test(expectedExceptions = EntityNotFoundException.class, expectedExceptionsMessageRegExp = "Invalid username or password")
    void testInvalidLogin1(){
       userService.login("The Incorrect Username", "The Correct Password");
    }

    @Test(expectedExceptions = EntityNotFoundException.class, expectedExceptionsMessageRegExp = "Invalid username or password")
    void testInvalidLogin2(){
       userService.login(testUser.getUsername(), "The incorrect password");
    }

    @Test(expectedExceptions = EntityNotFoundException.class, expectedExceptionsMessageRegExp = "Invalid username or password")
    void testInvalidLogin3(){
        userService.login(testUser.getUsername(),null);
    }

    @Test(expectedExceptions = EntityNotFoundException.class, expectedExceptionsMessageRegExp = "Invalid username or password")
    void testInvalidLogin4(){
        userService.login(null,"The Correct Password");
    }

}
