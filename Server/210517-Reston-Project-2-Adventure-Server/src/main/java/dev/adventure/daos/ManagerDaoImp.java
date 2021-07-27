package dev.adventure.daos;

import dev.adventure.entities.Manager;
import dev.adventure.exceptions.EntityNotFoundException;
import dev.adventure.exceptions.InvalidUsernameException;
import dev.adventure.utils.ConnectionUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ManagerDaoImp implements ManagerDao{
    @Override
    public Manager createManager(Manager manager) {
        try (Connection connection = ConnectionUtil.createConnection()){
            String sql = "insert into managers values (default, ?, ?, ?, ?)";
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, manager.getName());
            ps.setString(2, manager.getUsername());
            ps.setString(3, manager.getPasswordHash());
            ps.setString(4, manager.getPasswordSalt());
            ps.execute();
            ResultSet rs = ps.getGeneratedKeys();
            rs.next();
            manager.setId(rs.getInt("id"));
            return manager;
        } catch (SQLException m){
            m.printStackTrace();
            throw new InvalidUsernameException("Invalid Username");
        }
    }

    @Override
    public Manager selectManagerByID(int managerID) {
        try (Connection connection = ConnectionUtil.createConnection()){
            String sql = "select * from managers where id = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, managerID);
            ResultSet rs = ps.executeQuery();
            rs.next();
            Manager manager = new Manager();
            manager.setId(rs.getInt("id"));
            manager.setName(rs.getString("name"));
            manager.setUsername(rs.getString("username"));
            manager.setPasswordHash(rs.getString("password_hash"));
            manager.setPasswordSalt(rs.getString("password_salt"));
            return manager;
        } catch (SQLException m) {
            m.printStackTrace();
            throw new EntityNotFoundException("There was an error finding the manager");
        }
    }

    @Override
    public Manager selectManagerByUsername(String username) {
        try (Connection connection = ConnectionUtil.createConnection()) {
            String sql = "select * from managers where username = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            rs.next();
            Manager manager = new Manager();
            manager.setId(rs.getInt("id"));
            manager.setName(rs.getString("name"));
            manager.setUsername(rs.getString("username"));
            manager.setPasswordHash(rs.getString("password_hash"));
            manager.setPasswordSalt(rs.getString("password_salt"));
            return manager;

        } catch (SQLException m) {
            m.printStackTrace();
            throw new EntityNotFoundException("There was an error finding the manager");
        }
    }

    @Override
    public List<Manager> getAllManagers() {
        try (Connection connection = ConnectionUtil.createConnection()){
            String sql = "select * from managers";
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            List<Manager> managers = new ArrayList<>();
            while (rs.next()){
                Manager manager = new Manager(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("username"),
                        rs.getString("password_hash"),
                        rs.getString("password_salt")
                );
                managers.add(manager);
            }
            if (managers.size() == 0){
                System.out.println("Manager information could not be found at this time");
                throw new EntityNotFoundException("Manager information could not be found at this time");
            }
            return managers;
        } catch (SQLException m){
            m.printStackTrace();
            throw new EntityNotFoundException("Manager information could not be found at this time");
        }
    }

    @Override
    public Manager updateManager(Manager manager) {
        try (Connection connection = ConnectionUtil.createConnection()){
            String sql = "update managers set \"name\" = ?, username = ?, password_hash = ?, password_salt = ? where id = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, manager.getName());
            ps.setString(2, manager.getUsername());
            ps.setString(3, manager.getPasswordHash());
            ps.setString(4, manager.getPasswordSalt());
            ps.setInt(5, manager.getId());
            ps.executeUpdate();
            return manager;
        } catch (SQLException m){
            m.printStackTrace();
            throw new EntityNotFoundException("Manager information could not be updated at this time");
        }
    }

    @Override
    public boolean deleteManagerByID(int managerID) {
        try (Connection connection = ConnectionUtil.createConnection()){
            String sql = "delete from managers where id = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, managerID);
            ps.execute();
            return true;
        } catch (SQLException m) {
            m.printStackTrace();
            throw new EntityNotFoundException("The manager's information could not be deleted at this time");
        }
    }
}
