package com.example.octoberexambattleships.domain.model.binding;

import com.example.octoberexambattleships.util.validation.UniqueEmail;
import com.example.octoberexambattleships.util.validation.UniqueUsername;

import javax.validation.constraints.*;

public class UserRegisterBindingModel {

    @UniqueUsername
    @NotNull
    @Size(min = 3, max = 10, message = "The username must be between 3 and 10 characters long.")
    private String username;

    @NotNull
    @Size(min = 5, max = 20, message = "The name must be between 5 and 20 characters long.")
    private String fullName;

    @UniqueEmail
    @NotEmpty(message = "Enter а valid email address.")
    @Email(message = "Enter а valid email address.")
    private String email;

    @NotNull
    @Size(min = 3, message = "Password must be at least 3 characters long.")
    private String password;

    @NotNull
    @Size(min = 3, message = "Password must be at least 3 characters long.")
    private String confirmPassword;

    public UserRegisterBindingModel(){}

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
}
