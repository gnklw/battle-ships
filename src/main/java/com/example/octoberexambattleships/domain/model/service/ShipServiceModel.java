package com.example.octoberexambattleships.domain.model.service;

import com.example.octoberexambattleships.domain.entity.Categories;

import java.time.LocalDateTime;

public class ShipServiceModel {

    private Long id;
    private String name;
    private Integer power;
    private Integer health;
    private LocalDateTime created;
    private Categories category;

    public ShipServiceModel() {}

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

    public Integer getPower() {
        return power;
    }

    public void setPower(Integer power) {
        this.power = power;
    }

    public Integer getHealth() {
        return health;
    }

    public void setHealth(Integer health) {
        this.health = health;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public Categories getCategory() {
        return category;
    }

    public void setCategory(Categories category) {
        this.category = category;
    }
}
