package dev.adventure.services.claim_services;

import dev.adventure.entities.Claim;

import java.util.ArrayList;
import java.util.List;

public interface ClaimService {

    Claim registerClaim(Claim claim);
    ArrayList<Claim> retriveAllClaims();
    ArrayList<Claim> getAllClaimsByUserId(int user_id);
    Claim getClaimByID(int claimID);
    Claim updateClaim(Claim claim);

}
