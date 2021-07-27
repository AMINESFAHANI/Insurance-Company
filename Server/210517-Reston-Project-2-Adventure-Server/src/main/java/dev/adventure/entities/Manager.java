package dev.adventure.entities;

import org.jetbrains.annotations.NotNull;

public class Manager implements Comparable<Manager>{

    private int id;
    private String name;
    private String username;
    private String passwordHash;
    private String passwordSalt;

    public Manager(){}

    public Manager(int id, String name, String username, String passwordHash, String passwordSalt) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.passwordHash = passwordHash;
        this.passwordSalt = passwordSalt;
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public String getPasswordSalt() {
        return passwordSalt;
    }

    public void setPasswordSalt(String passwordSalt) {
        this.passwordSalt = passwordSalt;
    }

    @Override
    public String toString() {
        return "Manager{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", username='" + username + '\'' +
                '}';
    }

    @Override
    public int compareTo(@NotNull Manager o) {
        if(this.id < o.getId()){
            return -1;
        }
        else if(this.id > o.getId()){
            return 1;
        }
        return 0;
    }
}

