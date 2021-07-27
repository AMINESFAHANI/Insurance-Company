package dev.adventure.controllers;

import com.google.gson.Gson;
import dev.adventure.entities.Claim;
import dev.adventure.entities.Manager;
import dev.adventure.exceptions.EntityNotFoundException;
import dev.adventure.services.claim_services.ClaimService;
import io.javalin.http.Handler;
import dev.adventure.exceptions.ResourceNotFound;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import com.google.gson.Gson;
import io.javalin.http.Handler;
import java.util.Set;

public class ClaimController {

    private Gson gson = new Gson();
    private ClaimService claimService;


    public ClaimController(ClaimService claimService) {
        this.claimService = claimService;
    }

    public Handler hello = (ctx) -> {ctx.result("hello");};

    public Handler createClaim = (ctx) -> {
        try {
            Claim claim = this.gson.fromJson(ctx.body(), Claim.class);
            if (claim == null) {
                throw new ResourceNotFound("Can not create claim with no data");
            }
            int user_id= claim.getUserId();
            claim = this.claimService.registerClaim(claim);
            if (claim == null) {
                throw new ResourceNotFound("Can not create claim for user "+user_id);
            }
            String claimJSON = gson.toJson(claim);
            ctx.status(201);
            ctx.result(claimJSON);
        } catch (ResourceNotFound resourceNotFound) {
            ctx.result(resourceNotFound.getMessage());
            ctx.status(404);
        }
    };


    public Handler getAllClaims = (ctx) -> {
        try {
            ArrayList<Claim> claims = this.claimService.retriveAllClaims();
            if (claims.size() == 0) {
                throw new ResourceNotFound("There are no claims in the database");
            }
            String claimJSON = this.gson.toJson(claims);
            ctx.result(claimJSON);
        } catch (ResourceNotFound resourceNotFound) {
            ctx.result(resourceNotFound.getMessage());
            ctx.status(404);
        }
    };

    public Handler getAllClaimsByUserId = (ctx) -> {
        try {
            int user_id = Integer.parseInt(ctx.pathParam("user_id"));
            ArrayList<Claim> claims = this.claimService.getAllClaimsByUserId(user_id);
            if (claims == null || claims.size()==0) {
                throw new ResourceNotFound("No claim exists in the database for this user: " + user_id);
            }
            String claimJSON = this.gson.toJson(claims);
            ctx.result(claimJSON);
        } catch (ResourceNotFound resourceNotFound) {
            ctx.result(resourceNotFound.getMessage());
            ctx.status(404);
        }
    };

    public Handler updateClaim = ctx ->{
        try{
            int id = Integer.parseInt(ctx.pathParam("id"));
            Gson gson = new Gson();
            Claim claim = gson.fromJson(ctx.body(), Claim.class);
            claim.setId(id);
            this.claimService.updateClaim(claim);
            String claimJSON = gson.toJson(claim);
            ctx.result(claimJSON);
            ctx.status(200);

        } catch (EntityNotFoundException e){
            ctx.result(e.getMessage());
            ctx.status(404);
        }
    };
}
