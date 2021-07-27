package dev.adventure.daotests;

import dev.adventure.daos.UserDao;
import dev.adventure.daos.UserDaoImpl;
import dev.adventure.entities.User;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class UserDaoTest {

    static UserDao userDao = new UserDaoImpl();
    static User testUser = new User(0, "Barbarian", "TestyMcTest", "UserMcUsername", "passHash", "passSalt", 1);
    static User testUser2 = new User(0, "Barbarian2", "Testy2McTest2", "User2McUsername2", "passHash", "passSalt", 1);

    // The only database requirement is that there is a plan with id 1
    @Test()
    void testCreateUser(){
        User user = userDao.createUser(testUser);
        Assert.assertNotEquals(user.getId(),0);
    }

    @Test(dependsOnMethods = "testCreateUser")
    void testGetUser(){
        User user = userDao.readUserById(testUser.getId());
        Assert.assertEquals(user.getName(),"TestyMcTest");
    }

    @Test()
    void testGetUserByIdInvalid(){
        User user = userDao.readUserById(99999);
        Assert.assertNull(user);
    }

    @Test(dependsOnMethods = "testCreateUser")
    void testGetUserByUsername(){
        User user = userDao.readUserByUsername(testUser.getUsername());
        Assert.assertEquals(user.getName(),"TestyMcTest");

    }

    @Test()
    void testGetUserByUsernameInvalid(){
        User user = userDao.readUserByUsername("ASDFASDIOPHGAPSDGPIASHDGPASDGIOAHSDGPOIHADSPGIOASHDGPIASHDGPIOASDHGAOPSDGH");
        Assert.assertNull(user);
    }

    @Test(dependsOnMethods = {"testGetUserByUsername","testGetUser"})
    void testUpdateUser(){
        testUser.setName("UpdateTest");
        userDao.updateUser(testUser);
        Assert.assertEquals(testUser.getName(),"UpdateTest");
    }

    @Test(dependsOnMethods = "testCreateUser")
    void testGetAllUsers(){
        userDao.createUser(testUser2);
        List<User> users = userDao.readAllUsers();
        Assert.assertTrue(users.size()>=2);
    }

    @Test(dependsOnMethods = {"testGetAllUsers", "testUpdateUser"})
    void testDeleteUser(){
        Assert.assertTrue(userDao.deleteUser(testUser.getId()));
        Assert.assertTrue(userDao.deleteUser(testUser2.getId()));
    }

}
