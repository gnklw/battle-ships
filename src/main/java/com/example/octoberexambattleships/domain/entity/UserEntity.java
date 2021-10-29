package com.example.octoberexambattleships.domain.entity;

import javax.persistence.*;
import java.util.Set;

@Entity(name = "users")
public class UserEntity extends BaseEntity {

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String fullName;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, unique = true)
    private String email;

    @OneToMany(targetEntity = ShipEntity.class, mappedBy = "user")
    private Set<ShipEntity> ships;

    public UserEntity() {}

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<ShipEntity> getShips() {
        return ships;
    }

    public void setShips(Set<ShipEntity> ships) {
        this.ships = ships;
    }
}
