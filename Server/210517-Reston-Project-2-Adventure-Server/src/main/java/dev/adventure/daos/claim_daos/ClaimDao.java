package dev.adventure.daos.claim_daos;

import dev.adventure.entities.Claim;

import java.util.ArrayList;
import java.util.List;

public interface ClaimDao {

    Claim createClaim(Claim claim);
    ArrayList<Claim> getAllClaims();
    ArrayList<Claim> getAllClaimsByUserId(int user_id);
    Claim getClaimByID(int claimID);
    Claim updateClaim(Claim claim);
}
