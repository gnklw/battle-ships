package com.example.octoberexambattleships.domain.model.binding;

import com.example.octoberexambattleships.domain.entity.Categories;
import com.example.octoberexambattleships.util.validation.UniqueShipName;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.*;
import java.time.LocalDateTime;

public class AddShipBindingModel {

    @UniqueShipName
    @NotNull
    @Size(min = 2, max = 10, message = "The name must be between 2 and 10 characters long.")
    private String name;

    @NotNull(message = "The power cannot be empty, please enter a valid value.")
    @Positive(message = "Power must be a positive value.")
    private Integer power;

    @NotNull(message = "The health cannot be empty, please enter a valid value.")
    @Positive(message = "Health must be a positive value.")
    private Integer health;

    @NotNull(message = "The field cannot be empty, please enter a valid date.")
    @PastOrPresent(message = "The date and time cannot be in the future.")
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime created;

    @NotNull(message = "You must specify a category.")
    private Categories category;

    public AddShipBindingModel(){}

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
