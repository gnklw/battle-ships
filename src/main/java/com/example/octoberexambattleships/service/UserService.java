package com.example.octoberexambattleships.service;

import com.example.octoberexambattleships.domain.entity.UserEntity;
import com.example.octoberexambattleships.domain.model.service.UserServiceModel;

public interface UserService {

    UserServiceModel findByUsernameAndPassword(String username, String password);

    UserEntity findById(Long id);

    boolean findByEmail(String email);

    boolean findByUsername(String username);

    UserServiceModel register(UserServiceModel user);

}
