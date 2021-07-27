package dev.adventure.app;

import dev.adventure.controllers.ClaimController;
import dev.adventure.controllers.ManagerController;
import dev.adventure.controllers.PlanController;
import dev.adventure.controllers.UserController;
import dev.adventure.daos.*;
import dev.adventure.daos.claim_daos.ClaimDao;
import dev.adventure.daos.claim_daos.ClaimDaoPostgres;
import dev.adventure.services.*;
import dev.adventure.services.claim_services.ClaimService;
import dev.adventure.services.claim_services.ClaimServiceIMPL;
import io.javalin.Javalin;

public class App {
    public static void main(String[] args) {

        Javalin app = Javalin.create(config -> {
            config.enableCorsForAllOrigins();
            config.enableDevLogging();
        });

        PlanDao planDao = new PlanDaoImp();
        PlanService planService = new PlanServiceImp(planDao);
        PlanController planController = new PlanController(planService);
        ClaimDao claimDao = new ClaimDaoPostgres();
        ClaimService claimService = new ClaimServiceIMPL(claimDao);
        ClaimController claimController = new ClaimController(claimService);
        UserDao userDao = new UserDaoImpl();
        UserService userService = new UserServiceImp(userDao);
        UserController userController = new UserController(userService);
        ManagerDao managerDao = new ManagerDaoImp();
        ManagerService managerService = new ManagerServiceImp(managerDao);
        ManagerController managerController = new ManagerController(managerService);


        app.get("/hello",claimController.hello);

        app.get("/claims", claimController.getAllClaims);

        app.get("/claims/:user_id", claimController.getAllClaimsByUserId);

        app.post("/claims",claimController.createClaim);

        app.post("/claims/managers/:id", claimController.updateClaim);

        app.post("plans", planController.createPlan);

        app.get("/plans", planController.getAllPlans);

        app.get("/plans/:id", planController.getPlanByID);

        app.put("/plans/:id", planController.updatePlan);

        app.delete("/plans/:id", planController.deletePlan);

        app.post("/managers", managerController.createManager);

        app.post("/login/managers", managerController.loginManager);

        app.get("/managers/:id", managerController.getManagerByID);

        app.get("/managers", managerController.getAllManagers);

        app.put("/managers/:id", managerController.updateManager);

        app.delete("/managers/:id", managerController.deleteManagerByID);

        app.post("/login", userController.login);

        app.get("/users", userController.getAllUsers);

        app.get("/users/:id", userController.getUserById);

        app.post("/users", userController.createUser);

        app.put("/users/:id/plans/:pid", userController.updatePlan);

        app.start();

    };
};
