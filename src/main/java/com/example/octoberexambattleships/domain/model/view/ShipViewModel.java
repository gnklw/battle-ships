package com.example.octoberexambattleships.domain.model.view;

import com.example.octoberexambattleships.domain.model.service.UserServiceModel;

public class ShipViewModel {

    private Long id;
    private String name;
    private int health;
    private int power;
    private UserServiceModel user;

    public ShipViewModel(){}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public UserServiceModel getUser() {
        return user;
    }

    public void setUser(UserServiceModel user) {
        this.user = user;
    }
}
