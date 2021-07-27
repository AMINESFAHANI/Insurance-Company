package dev.adventure.services;

import dev.adventure.entities.Manager;

import java.util.List;

public interface ManagerService {

    Manager createManager(Manager manager);

    Manager getManagerByID(int managerID);

    Manager getManagerByUsername(String username);

    List<Manager> getAllManagers();

    Manager updateManger(Manager manager);

    boolean deleteManagerByID(int managerID);

    int loginManager(String username, String password);

}
