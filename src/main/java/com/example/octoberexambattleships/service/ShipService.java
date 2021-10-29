package com.example.octoberexambattleships.service;

import com.example.octoberexambattleships.domain.model.binding.HomeBindingModel;
import com.example.octoberexambattleships.domain.model.service.ShipServiceModel;
import com.example.octoberexambattleships.domain.model.service.UserServiceModel;
import com.example.octoberexambattleships.domain.model.view.ShipViewModel;

import java.util.Collection;

public interface ShipService {

    void add(ShipServiceModel ship, UserServiceModel currentUser);

    Collection<ShipViewModel> getAllShips();

    void fight(HomeBindingModel ship);

    boolean findByShipName(String shipName);
}
