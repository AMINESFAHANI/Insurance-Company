package dev.adventure.daos;

import dev.adventure.entities.User;

import java.util.List;

public interface UserDao {
    // CRUD operations

    User createUser(User user);
    User readUserById(int id);
    User readUserByUsername(String userName);
    List<User> readAllUsers();
    User updateUser(User user);
    boolean deleteUser(int id);
}
