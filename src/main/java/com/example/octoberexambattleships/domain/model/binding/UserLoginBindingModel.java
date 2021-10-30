package com.example.octoberexambattleships.domain.model.binding;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class UserLoginBindingModel {

    @NotNull
    @Size(min = 3, max = 10, message = "The username must be between 3 and 10 characters long.")
    private String username;

    @NotNull
    @Size(min = 3, message = "Password must be at least 3 characters long.")
    private String password;

    public UserLoginBindingModel() {}

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
