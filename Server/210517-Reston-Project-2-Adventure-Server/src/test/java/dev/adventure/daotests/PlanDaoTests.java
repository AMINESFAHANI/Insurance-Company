package dev.adventure.daotests;

import dev.adventure.daos.PlanDao;
import dev.adventure.daos.PlanDaoImp;
import dev.adventure.entities.Plan;
import dev.adventure.utils.ConnectionUtil;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class PlanDaoTests {

    static PlanDao planDao = new PlanDaoImp();
    Plan testPlan = new Plan(0, "gold plan", "Premium", 500, 100);

    static void resetPlans(){
        try (Connection connection = ConnectionUtil.createConnection()){
            String sql = "drop table if exists plan;\n" +
                    "create table plan(\n" +
                    "\tid serial primary key,\n" +
                    "\tplan_name varchar(50),\n" +
                    "\tplan_type varchar(50),\n" +
                    "\tdeductible float,\n" +
                    "\tpremium float\n" +
                    ")";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.execute();
            System.out.println("plan table was reset successfully");
        } catch (SQLException m) {
            System.out.println("plan table was not reset");
        }
    }

    @BeforeClass
    void setup(){
        resetPlans();
    }

    @AfterClass
    void teardown(){
        resetPlans();
    }

    // this test is used to make sure test plans can be added to the database
    @Test(priority = 1)
    void createPlan() {
        Plan result = planDao.createPlan(testPlan);
        Assert.assertNotEquals(result.getPlanID(), 0);
    }

    @Test(priority = 2)
    void selectPlanByID() {
        Plan selectedResult = planDao.getPlanByID(1);
        Assert.assertEquals(selectedResult.getPlanID(), 1);
    }

    @Test(priority = 3)
    void selectAllPlans() {
        Plan testPlan2 = new Plan(0, "silver plan", "Mid-Tier", 700, 70);
        Plan testPlan3 = new Plan(0, "bronze plan", "Entry", 300, 30);
        planDao.createPlan(testPlan2);
        planDao.createPlan(testPlan3);
        List<Plan> plans = planDao.getAllPlans();
        Assert.assertTrue(plans.size() >= 3);

    }

}
