package dev.adventure.entities;
import dev.adventure.utils.Password;
import org.jetbrains.annotations.NotNull;

import java.lang.Comparable;

public class User implements Comparable<User>{
    private int id;
    private String occupation;
    private String name;
    private String username;
    private String passwordHash;
    private String passwordSalt;
    private int planId;

    public User(){
    }

    public User(int id, String occupation, String name, String username, String passwordHash, String passwordSalt, int planId) {
        this.id = id;
        this.occupation = occupation;
        this.name = name;
        this.username = username;
        this.passwordHash = passwordHash;
        this.passwordSalt = passwordSalt;
        this.planId = planId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
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

    public int getPlanId() {
        return planId;
    }

    public void setPlanId(int planId) {
        this.planId = planId;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", occupation='" + occupation + '\'' +
                ", name='" + name + '\'' +
                ", username='" + username + '\'' +
                ", passwordHash='" + passwordHash + '\'' +
                ", passwordSalt='" + passwordSalt + '\'' +
                ", planId=" + planId +
                '}';
    }

    @Override
    public int compareTo(@NotNull User o) {
        if(this.id < o.getId()){
            return -1;
        }
        else if(this.id > o.getId()){
            return 1;
        }
        return 0;
    }
}
