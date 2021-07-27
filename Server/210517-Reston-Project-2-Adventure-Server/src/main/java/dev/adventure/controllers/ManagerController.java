package dev.adventure.controllers;

import com.google.gson.Gson;
import dev.adventure.entities.Manager;
import dev.adventure.exceptions.EntityNotFoundException;
import dev.adventure.exceptions.InvalidUsernameException;
import dev.adventure.services.ManagerService;
import dev.adventure.utils.Password;
import io.javalin.http.Handler;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class ManagerController {

    private ManagerService managerService;

    public ManagerController(ManagerService managerService){
        this.managerService = managerService;
    }

    public Handler loginManager = ctx ->{
        String body = ctx.body();
        Gson gson = new Gson();
        Map<String, String> credentials = gson.fromJson(body, Map.class);
        try {
            int id = this.managerService.loginManager(credentials.get("username"), credentials.get("password"));
            ctx.result("{\"id\":"+id+"}");
            ctx.status(200);
        }catch (EntityNotFoundException e){
            e.printStackTrace();
            ctx.result("{\"id\":"+(-1)+"}");
            ctx.status(404);
        }
    };

    public Handler createManager = ctx->{
        try {
            Gson gson = new Gson();
            Map<String, String> password = gson.fromJson(ctx.body(), Map.class);
            String[] passwordHash = Password.hashGriddle(password.get("password"));
            Manager manager = gson.fromJson(ctx.body(), Manager.class);
            manager.setPasswordHash(passwordHash[0]);
            manager.setPasswordSalt(passwordHash[1]);
            managerService.createManager(manager);
            String managerJSON = gson.toJson(manager);
            ctx.result(managerJSON);
            ctx.status(201);
        } catch (InvalidUsernameException e){
            ctx.result(e.getMessage());
            ctx.status(400);
        }
    };

    public Handler getAllManagers = ctx->{
        try {
            List<Manager> managers = new ArrayList<>();
            managers = this.managerService.getAllManagers();
            Gson gson = new Gson();
            String managersJSON = gson.toJson(managers);
            ctx.result(managersJSON);
            ctx.status(200);
            ctx.contentType("application/json");
        } catch (EntityNotFoundException e){
            ctx.result(e.getMessage());
            ctx.status(404);
        }
    };

    public Handler getManagerByID = ctx->{
        try {
            int id = Integer.parseInt(ctx.pathParam("id"));
            Manager manager = this.managerService.getManagerByID(id);
            Gson gson = new Gson();
            String managerJSON = gson.toJson(manager);
            ctx.result(managerJSON);
            ctx.status(200);
        } catch (EntityNotFoundException e){
            ctx.result(e.getMessage());
            ctx.status(404);
        }
    };

    public Handler updateManager = ctx->{
        try{
            int id = Integer.parseInt(ctx.pathParam("id"));
            Gson gson = new Gson();
            Manager manager = gson.fromJson(ctx.body(), Manager.class);
            manager.setId(id);
            this.managerService.updateManger(manager);
            String managerJSON = gson.toJson(manager);
            ctx.result(managerJSON);
            ctx.status(200);

        } catch (EntityNotFoundException e){
            ctx.result(e.getMessage());
            ctx.status(404);
        }
    };

    public Handler deleteManagerByID = ctx->{
        try{
            int id = Integer.parseInt(ctx.pathParam("id"));
            this.managerService.deleteManagerByID(id);
            ctx.result("Manager deleted");
            ctx.status(200);
        } catch (EntityNotFoundException e){
            ctx.result(e.getMessage());
            ctx.status(404);
        }
    };

}
