package dev.adventure.services;

import dev.adventure.daos.UserDao;
import dev.adventure.entities.User;
import dev.adventure.exceptions.EntityNotFoundException;
import dev.adventure.utils.Password;

import java.util.List;

public class UserServiceImp implements UserService{

    private UserDao userDao;

    public UserServiceImp(UserDao userDao){
        this.userDao = userDao;
    }

    @Override
    public int login(String username, String password) {
        User user = this.userDao.readUserByUsername(username);

        if(username==null || password==null) throw new EntityNotFoundException("Invalid username or password");
        if(user==null) throw new EntityNotFoundException("Invalid username or password");
        String [] hashAndSalt = Password.hashGriddle(password);

        if(Password.checkPass(password, user.getPasswordHash(), user.getPasswordSalt())){
            return user.getId();
        }
        else{
            throw new EntityNotFoundException("Invalid username or password");
        }

    }

    @Override
    public User getUserById(int id) {
        return this.userDao.readUserById(id);
    }

    @Override
    public User getUserByUsername(String username) {
        return this.userDao.readUserByUsername(username);
    }

    @Override
    public List<User> getAllUsers() {
        return this.userDao.readAllUsers();
    }

    @Override
    public User createNewUser(User user) {
        user = userDao.createUser(user);
        return user; }

    @Override
    public User updateUser(User user) {
        user = userDao.updateUser(user);
        return user;
    }
}
