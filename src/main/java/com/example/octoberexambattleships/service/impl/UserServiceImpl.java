package com.example.octoberexambattleships.service.impl;

import com.example.octoberexambattleships.domain.entity.UserEntity;
import com.example.octoberexambattleships.domain.model.service.UserServiceModel;
import com.example.octoberexambattleships.repo.UserRepo;
import com.example.octoberexambattleships.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepo userRepo;
    private final ModelMapper modelMapper;

    public UserServiceImpl(UserRepo userRepo, ModelMapper modelMapper) {
        this.userRepo = userRepo;
        this.modelMapper = modelMapper;
    }

    @Override
    public UserServiceModel findByUsernameAndPassword(String username, String password) {
        return this.userRepo
                .findByUsernameAndPassword(username, password)
                .map(user -> this.modelMapper.map(user, UserServiceModel.class))
                .orElse(null);
    }

    @Override
    public UserServiceModel register(UserServiceModel user) {
        return modelMapper.map(
                this.userRepo.save(
                        this.modelMapper.map(user, UserEntity.class)), UserServiceModel.class);
    }

    @Override
    public UserEntity findById(Long id) {
        return this.userRepo.findById(id).get();
    }

    @Override
    public boolean findByEmail(String email) {
        return this.userRepo.findByEmail(email).isEmpty();
    }

    @Override
    public boolean findByUsername(String username) {
        return this.userRepo.findByUsername(username).isEmpty();
    }
}
