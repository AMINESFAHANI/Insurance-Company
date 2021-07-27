package dev.adventure.servicetests;

import dev.adventure.daos.PlanDao;
import dev.adventure.daos.PlanDaoImp;
import dev.adventure.entities.Plan;
import dev.adventure.exceptions.EntityNotFoundException;
import dev.adventure.services.PlanService;
import dev.adventure.services.PlanServiceImp;
import dev.adventure.utils.ConnectionUtil;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PlanServiceTests {

    /*These tests are to make sure exceptions are thrown correctly*/

    Plan badPlan = new Plan();

    PlanDao planDao = new PlanDaoImp();
    PlanService planService = new PlanServiceImp(planDao);

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


    @Test(priority = 1, expectedExceptions = {EntityNotFoundException.class}, expectedExceptionsMessageRegExp = "There was an error trying to get the plan")
    void getPlanByIDFail(){
        planService.getPlanByID(99999);
    }

    @Test(priority = 2, expectedExceptions = {EntityNotFoundException.class}, expectedExceptionsMessageRegExp = "There was an error trying to get the plan")
    void updatePlanFail(){
        planService.updatePlan(badPlan);
    }

    @Test(priority = 3, expectedExceptions = {EntityNotFoundException.class}, expectedExceptionsMessageRegExp = "There was an error trying to get the plan")
    void deletePlanFail(){
        planService.deletePlanByID(badPlan.getPlanID());
    }

}
