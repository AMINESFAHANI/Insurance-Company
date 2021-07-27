package dev.adventure.services;

import dev.adventure.entities.User;

import java.util.List;

public interface UserService {

    // methods unique to service
    int login(String username, String password);

    //Dao methods
    User getUserById(int id);

    User getUserByUsername(String username);

    List<User> getAllUsers();

    User createNewUser(User user);

    User updateUser(User user);

}
