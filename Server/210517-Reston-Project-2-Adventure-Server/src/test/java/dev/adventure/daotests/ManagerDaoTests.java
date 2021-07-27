package dev.adventure.daotests;

import dev.adventure.daos.ManagerDao;
import dev.adventure.daos.ManagerDaoImp;
import dev.adventure.entities.Manager;
import dev.adventure.utils.ConnectionUtil;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class ManagerDaoTests {

    static ManagerDao managerDao = new ManagerDaoImp();

    Manager manager1 = new Manager(0,"name","username","hash", "salt");

    static void resetManagers(){
        try (Connection connection = ConnectionUtil.createConnection()){
            String sql = "drop table if exists managers;\n" +
                    "create table managers(\n" +
                    "\tid serial primary key,\n" +
                    "\t\"name\" varchar(50),\n" +
                    "\tusername varchar(50) unique,\n" +
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

    @Test(priority = 1)
    void createManager(){
        Manager newManager = managerDao.createManager(manager1);
        Assert.assertNotEquals(newManager.getId(),0);
    }

    @Test(priority = 2)
    void selectManagerByID(){
        Manager selectedManager = managerDao.selectManagerByID(1);
        Assert.assertEquals(selectedManager.getId(),1);
    }

    @Test(priority = 3)
    void selectAllManagers(){
        Manager manager2 = new Manager(0,"name2","username2","hash", "salt");
        Manager manager3 = new Manager(0,"name3","username3","hash", "salt");
        managerDao.createManager(manager2);
        managerDao.createManager(manager3);
        List<Manager> managers = managerDao.getAllManagers();
        System.out.println(managers.size());
        Assert.assertTrue(managers.size() >= 3);

    }

    @Test(priority = 4)
    void updateManagerByID(){
        Manager updatedManager = new Manager(1,"name4","username4","hash", "salt");
        managerDao.updateManager(updatedManager);
        System.out.println(managerDao.selectManagerByID(1).getName());
        updatedManager = managerDao.selectManagerByID(1);
        Assert.assertEquals(updatedManager.getName(), "name4");
    }

    @Test(priority = 5)
    void deleteManagerByID(){
        Assert.assertTrue(managerDao.deleteManagerByID(1));
    }

    @Test(priority = 6)
    void selectManagerByUsername(){
        Manager selectedManager = managerDao.selectManagerByUsername("username2");
        Assert.assertEquals(selectedManager.getUsername(), "username2");
    }

}

