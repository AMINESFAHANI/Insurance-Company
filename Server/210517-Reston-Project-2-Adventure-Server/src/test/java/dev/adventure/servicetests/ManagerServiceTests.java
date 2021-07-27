package dev.adventure.servicetests;

import dev.adventure.daos.ManagerDao;
import dev.adventure.daos.ManagerDaoImp;
import dev.adventure.entities.Manager;
import dev.adventure.exceptions.EntityNotFoundException;
import dev.adventure.exceptions.InvalidUsernameException;
import dev.adventure.services.ManagerService;
import dev.adventure.services.ManagerServiceImp;
import dev.adventure.utils.ConnectionUtil;
import dev.adventure.utils.Password;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ManagerServiceTests {

    Manager badManager = new Manager();

    ManagerDao managerDao = new ManagerDaoImp();
    ManagerService managerService = new ManagerServiceImp(managerDao);

    static void resetManagers(){
        try (Connection connection = ConnectionUtil.createConnection()){
            String sql = "drop table if exists managers;\n" +
                    "create table managers(\n" +
                    "\tid serial primary key,\n" +
                    "\t\"name\" varchar(50) unique,\n" +
                    "\tusername varchar(50),\n" +
                    "\tpassword_hash varchar(200),\n" +
                    "\tpassword_salt varchar(200)\n" +
                    ")";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.execute();
            System.out.println("managers table was reset successfully");
        } catch (SQLException m) {
            System.out.println("managers table was not reset");
        }
    }

    @BeforeClass
    void setup(){
        resetManagers();
    }

    @AfterClass
    void teardown(){
        resetManagers();
    }

    @Test(priority = 1, expectedExceptions = {EntityNotFoundException.class}, expectedExceptionsMessageRegExp = "There was an error finding the manager")
    void getManagerByIDFail(){
        managerService.getManagerByID(badManager.getId());
    }

    @Test(priority = 2, expectedExceptions = {EntityNotFoundException.class}, expectedExceptionsMessageRegExp = "Manager information could not be found at this time")
    void getAllManagersFail(){
        managerService.getAllManagers();
    }

    @Test(priority = 3, expectedExceptions = {EntityNotFoundException.class}, expectedExceptionsMessageRegExp = "There was an error finding the manager")
    void updateManagerFail(){
        managerService.updateManger(badManager);
    }

    @Test(priority = 4, expectedExceptions = {EntityNotFoundException.class}, expectedExceptionsMessageRegExp = "There was an error finding the manager")
    void deleteManagerFail(){
        managerService.deleteManagerByID(badManager.getId());
    }

    @Test(priority = 5, expectedExceptions = {EntityNotFoundException.class}, expectedExceptionsMessageRegExp = "There was an error finding the manager")
    void getManagerByUsernameFail(){
        managerService.getManagerByUsername("does not exist");
    }

    @Test(priority = 6)
    void loginManager(){
        String[] hasSalt= Password.hashGriddle("password");
        Manager manager = new Manager();
        manager.setId(0);
        manager.setName("test");
        manager.setUsername("username");
        manager.setPasswordHash(hasSalt[0]);
        manager.setPasswordSalt(hasSalt[1]);
        System.out.println(manager);
        managerService.createManager(manager);
        int managerID = managerService.loginManager("username", "password");
        System.out.println(managerID);
        Assert.assertTrue(managerID > 0);
    }

    @Test(priority = 7, expectedExceptions = {EntityNotFoundException.class}, expectedExceptionsMessageRegExp = "Invalid username or password")
    void loginManagerBadUsername(){
        managerService.loginManager("uh oh spaghettios", "password");
    }

    @Test(priority = 8, expectedExceptions = {EntityNotFoundException.class}, expectedExceptionsMessageRegExp = "Invalid username or password")
    void loginManagerBadPassword(){
        managerService.loginManager("username", "I can't believe it's not butter");
    }

    @Test(priority = 9, expectedExceptions = {EntityNotFoundException.class}, expectedExceptionsMessageRegExp = "Invalid username or password")
    void loginManagerBadUsernameAndPassword(){
        managerService.loginManager("uh oh spaghettios", "I can't believe it's not butter");
    }

    @Test(priority = 10, expectedExceptions = {InvalidUsernameException.class}, expectedExceptionsMessageRegExp = "Invalid Username")
    void createManagerInvalidUsername(){
        Manager manager1 = new Manager(0,"name","username","hash", "salt");
        System.out.println(managerService.createManager(manager1));
        System.out.println(managerService.createManager(manager1));
    }

}
