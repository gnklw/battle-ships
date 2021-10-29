package com.example.octoberexambattleships.domain.model.binding;

import com.example.octoberexambattleships.domain.entity.Categories;
import com.example.octoberexambattleships.util.validation.UniqueShipName;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.*;
import java.time.LocalDateTime;

public class AddShipBindingModel {

    @UniqueShipName
    @NotNull
    @Size(min = 2, max = 10, message = "Name must be between 2 and 10 characters.")
    private String name;

    @NotNull(message = "Power cannot be empty")
    @Positive(message = "The power must be positive.")
    private Integer power;

    @NotNull(message = "Health cannot be empty")
    @Positive(message = "The health must be positive.")
    private Integer health;

    @NotNull(message = "Date cannot be empty")
    @PastOrPresent(message = "WTF")
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime created;

    @NotNull(message = "You must select the category.")
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
