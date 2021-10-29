package com.example.octoberexambattleships.service.impl;

import com.example.octoberexambattleships.domain.model.binding.HomeBindingModel;
import com.example.octoberexambattleships.domain.entity.CategoryEntity;
import com.example.octoberexambattleships.domain.entity.ShipEntity;
import com.example.octoberexambattleships.domain.entity.UserEntity;
import com.example.octoberexambattleships.domain.model.service.ShipServiceModel;
import com.example.octoberexambattleships.domain.model.service.UserServiceModel;
import com.example.octoberexambattleships.domain.model.view.ShipViewModel;
import com.example.octoberexambattleships.repo.ShipRepo;
import com.example.octoberexambattleships.service.CategoryService;
import com.example.octoberexambattleships.service.ShipService;
import com.example.octoberexambattleships.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ShipServiceImpl implements ShipService {

    private final ShipRepo shipRepo;
    private final ModelMapper modelMapper;
    private final CategoryService categoryService;
    private final UserService userService;

    public ShipServiceImpl(ShipRepo shipRepo, ModelMapper modelMapper, CategoryService categoryService,
                           UserService userService) {
        this.shipRepo = shipRepo;
        this.modelMapper = modelMapper;
        this.categoryService = categoryService;
        this.userService = userService;
    }

    @Override
    public void add(ShipServiceModel ship, UserServiceModel currentUser) {
        ShipEntity shipEntity = this.modelMapper.map(ship, ShipEntity.class);
        UserEntity user = this.userService.findById(currentUser.getId());
        CategoryEntity category = this.categoryService.findByCategory(ship.getCategory());

        shipEntity.setUser(user);
        shipEntity.setCategory(category);

        this.shipRepo.save(shipEntity);
    }

    //todo: taking ships differently
    @Override
    public Collection<ShipViewModel> getAllShips() {
        return this.shipRepo.findAll()
                .stream()
                .map(e -> {
                    ShipViewModel ship = this.modelMapper.map(e, ShipViewModel.class);
                    ship.setUser(modelMapper.map(e.getUser(), UserServiceModel.class));
                    return ship;
                })
                .collect(Collectors.toList());
    }

    @Override
    public void fight(HomeBindingModel ship) {
        Optional<ShipEntity> attacker =
                this.shipRepo.findById(ship.getAttackerShipId());
        Optional<ShipEntity> defender =
                this.shipRepo.findById(ship.getDefenderShipId());

        if (attacker.isEmpty() || defender.isEmpty()) {
            return;
        }

        ShipEntity _defender = defender.get();
        _defender.setHealth(_defender.getHealth() - attacker.get().getPower());

        if (_defender.getHealth() <= 0) {
            this.shipRepo.delete(_defender);
            return;
        }

        this.shipRepo.save(_defender);
    }

    @Override
    public boolean findByShipName(String shipName) {
        return this.shipRepo.findByName(shipName).isEmpty();
    }
}
