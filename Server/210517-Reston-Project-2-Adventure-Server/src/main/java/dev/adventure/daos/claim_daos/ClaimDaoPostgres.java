package dev.adventure.daos.claim_daos;

import dev.adventure.entities.Claim;
import dev.adventure.entities.Manager;
import dev.adventure.entities.Plan;
import dev.adventure.exceptions.EntityNotFoundException;
import dev.adventure.exceptions.ResourceNotFound;
import dev.adventure.utils.ConnectionUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClaimDaoPostgres implements ClaimDao {

    @Override
    public Claim createClaim(Claim claim) {
        try (Connection connection = ConnectionUtil.createConnection()) {
            String sql = "insert into claim values(default, ?, ?, ?, ?, ?)";
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            ps.setLong(1, claim.getDate());
            ps.setFloat(2, claim.getAmount());
            ps.setString(3, claim.getReason());
            ps.setString(4, claim.getStatus());
            ps.setInt(5,claim.getUserId());
            ps.execute();
            ResultSet rs = ps.getGeneratedKeys();
            rs.next();
            int key = rs.getInt(1);
            claim.setId(key);
            return claim;
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
            return null;
        }
    }


    @Override
    public ArrayList<Claim> getAllClaims() {
        try (Connection connection = ConnectionUtil.createConnection()) {
            String sql = "select * from claim";
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            ArrayList<Claim> claims = new ArrayList<Claim>();
            while (rs.next()) {
                Claim claim = new Claim(
                        rs.getInt("id"),
                        rs.getLong("claim_date"),
                        rs.getFloat("amount"),
                        rs.getString("reason"),
                        rs.getString("status"),
                        rs.getInt("user_id")
                );
                claims.add(claim);
            }
            return claims;
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
            return null;
        }

    }
    @Override
    public ArrayList<Claim> getAllClaimsByUserId(int user_id) {
        try (Connection connection = ConnectionUtil.createConnection()) {
            String sql = "select * from claim where user_id=?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1,user_id);
            ResultSet rs = ps.executeQuery();
            ArrayList<Claim> claims = new ArrayList<Claim>();
            while (rs.next()) {
                Claim claim = new Claim(
                        rs.getInt("id"),
                        rs.getLong("claim_date"),
                        rs.getFloat("amount"),
                        rs.getString("reason"),
                        rs.getString("status"),
                        rs.getInt("user_id")
                );
                claims.add(claim);
            }
            if (claims.size() == 0) {
                throw new ResourceNotFound("There are no claims in the database at this moment.");
            }
            return claims;
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
            return null;
        }

    }

    @Override
    public Claim getClaimByID(int claimID) {
        try (Connection connection = ConnectionUtil.createConnection()){
            String sql = "select * from claim where id = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, claimID);
            ResultSet rs = ps.executeQuery();
            rs.next();
            Claim claim = new Claim();
            claim.setId(rs.getInt("id"));
            claim.setDate(rs.getLong("claim_date"));
            claim.setAmount(rs.getFloat("amount"));
            claim.setReason(rs.getString("reason"));
            claim.setStatus(rs.getString("status"));
            claim.setUserId(rs.getInt("user_id"));
            return claim;
        } catch (SQLException m) {
            m.printStackTrace();
            throw new EntityNotFoundException("There was an error finding the claim");
        }
    }

    @Override
    public Claim updateClaim(Claim claim) {
        try (Connection connection = ConnectionUtil.createConnection()){
            String sql = "update claim set status = ? where id = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, claim.getStatus());
            ps.setInt(2, claim.getId());
            ps.executeUpdate();
            return claim;
        } catch (SQLException m){
            m.printStackTrace();
            throw new EntityNotFoundException("Claim information could not be updated at this time");
        }
    }

}

