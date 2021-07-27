package dev.adventure.services;

import dev.adventure.daos.ManagerDao;
import dev.adventure.entities.Manager;
import dev.adventure.entities.User;
import dev.adventure.exceptions.EntityNotFoundException;
import dev.adventure.exceptions.InvalidUsernameException;
import dev.adventure.utils.Password;

import java.sql.SQLException;
import java.util.List;

public class ManagerServiceImp implements ManagerService{

    private ManagerDao managerDao;

    public ManagerServiceImp(ManagerDao managerDao){
        this.managerDao = managerDao;
    }

    @Override
    public Manager createManager(Manager manager) {
        return managerDao.createManager(manager);

    }

    @Override
    public Manager getManagerByID(int managerID) {
        return managerDao.selectManagerByID(managerID);
    }

    @Override
    public Manager getManagerByUsername(String username) { return managerDao.selectManagerByUsername(username);}

    @Override
    public List<Manager> getAllManagers() {
        return managerDao.getAllManagers();
    }

    @Override
    public Manager updateManger(Manager manager) {
        Manager check = managerDao.selectManagerByID(manager.getId());
        return managerDao.updateManager(manager);
    }

    @Override
    public boolean deleteManagerByID(int managerID) {
        Manager check = managerDao.selectManagerByID(managerID);
        return managerDao.deleteManagerByID(managerID);
    }

    @Override
    public int loginManager(String username, String password) {
        try {
            Manager manager = this.managerDao.selectManagerByUsername(username);

            if (username == null || password == null) throw new EntityNotFoundException("Invalid username or password");
            if (manager == null) throw new EntityNotFoundException("Invalid username or password");
            String[] hashAndSalt = Password.hashGriddle(password);

            if (Password.checkPass(password, manager.getPasswordHash(), manager.getPasswordSalt())) {
                return manager.getId();
            } else {
                throw new EntityNotFoundException("Invalid username or password");
            }
        } catch (EntityNotFoundException e){
            throw new EntityNotFoundException("Invalid username or password");
        }
    }
}
