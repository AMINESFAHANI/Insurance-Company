package dev.adventure.daos;

import dev.adventure.entities.User;
import dev.adventure.utils.ConnectionUtil;
import org.postgresql.util.PSQLException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl implements UserDao {

    @Override
    public User createUser(User user) {
        try (Connection connection = ConnectionUtil.createConnection()) {
            String sql = "insert into \"public\".user (occupation, user_name, username, passwordhash, passwordsalt,plan_id) values (?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, user.getOccupation());
            ps.setString(2, user.getName());
            ps.setString(3, user.getUsername());
            ps.setString(4, user.getPasswordHash());
            ps.setString(5, user.getPasswordSalt());
            ps.setInt(6, user.getPlanId());

            ps.execute();
            ResultSet rs = ps.getGeneratedKeys();
            rs.next();
            user.setId(rs.getInt("id"));
            return user;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public User readUserById(int id) {
        try (Connection connection = ConnectionUtil.createConnection()) {
            String sql = "select * from \"public\".user where id = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            rs.next();
            User user = new User();
            user.setId(rs.getInt("id"));
            user.setOccupation(rs.getString("occupation"));
            user.setName(rs.getString("user_name"));
            user.setUsername(rs.getString("username"));
            user.setPasswordHash(rs.getString("passwordhash"));
            user.setPasswordSalt(rs.getString("passwordsalt"));
            user.setPlanId(rs.getInt("plan_id"));
            return user;

        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }
    }

    @Override
    public User readUserByUsername(String userName) {
        try (Connection connection = ConnectionUtil.createConnection()) {
            String sql = "select * from \"public\".user where username = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, userName);
            ResultSet rs = ps.executeQuery();
            rs.next();
            User user = new User();
            user.setId(rs.getInt("id"));
            user.setOccupation(rs.getString("occupation"));
            user.setName(rs.getString("user_name"));
            user.setUsername(rs.getString("username"));
            user.setPasswordHash(rs.getString("passwordhash"));
            user.setPasswordSalt(rs.getString("passwordsalt"));
            user.setPlanId(rs.getInt("plan_id"));
            return user;

        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }
    }


    public List<User> readAllUsers() {
        try (Connection connection = ConnectionUtil.createConnection()) {
            String sql = "select * from \"public\".user";
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            List<User> userList = new ArrayList<>();

            while (rs.next()) {
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setOccupation(rs.getString("occupation"));
                user.setName(rs.getString("user_name"));
                user.setUsername(rs.getString("username"));
                user.setPasswordHash(rs.getString("passwordhash"));
                user.setPasswordSalt(rs.getString("passwordsalt"));
                user.setPlanId(rs.getInt("plan_id"));
                userList.add(user);
            }
            return userList;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }
    }

    @Override
    public User updateUser(User user) {
        try (Connection connection = ConnectionUtil.createConnection()) {
            String sql = "update \"public\".user set occupation=?, user_name=?, username=?, passwordhash=?, passwordsalt=?, plan_id=? where id=?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, user.getOccupation());
            ps.setString(2, user.getName());
            ps.setString(3, user.getUsername());
            ps.setString(4, user.getPasswordHash());
            ps.setString(5, user.getPasswordSalt());
            ps.setInt(6, user.getPlanId());
            ps.setInt(7,user.getId());

            // returns the row index or 0 for failure
            ps.executeUpdate();
            return user;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean deleteUser(int id) {
        try (Connection connection = ConnectionUtil.createConnection()) {
            String sql = "delete from \"user\" where id=?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            ps.execute();
            return true;

        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        }

    }
}