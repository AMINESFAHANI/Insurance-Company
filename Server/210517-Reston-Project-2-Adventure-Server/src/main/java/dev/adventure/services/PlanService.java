package dev.adventure.services;

import dev.adventure.entities.Plan;

import java.util.List;

public interface PlanService {

    Plan createPlan(Plan plan);

    Plan getPlanByID(int planID);

    List<Plan> getAllPlans();

    Plan updatePlan(Plan plan);

    boolean deletePlanByID(int planID);

}
