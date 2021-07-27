package dev.adventure.daos;

import dev.adventure.entities.Plan;

import java.util.List;

public interface PlanDao {

    // create

    Plan createPlan(Plan plan);

    // read

    Plan getPlanByID(int planID);

    List<Plan> getAllPlans();

    // update

    Plan updatePlan(Plan plan);

    // delete

    boolean deletePlanByID(int planID);

}
