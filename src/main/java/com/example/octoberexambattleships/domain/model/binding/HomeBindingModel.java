package com.example.octoberexambattleships.domain.model.binding;

import javax.validation.constraints.NotNull;

public class HomeBindingModel {

    @NotNull(message = "You must select the ship.")
    private Long attackerShipId;

    @NotNull(message = "You must select the ship.")
    private Long defenderShipId;

    public HomeBindingModel(){}

    public Long getAttackerShipId() {
        return attackerShipId;
    }

    public void setAttackerShipId(Long attackerShipId) {
        this.attackerShipId = attackerShipId;
    }

    public Long getDefenderShipId() {
        return defenderShipId;
    }

    public void setDefenderShipId(Long defenderShipId) {
        this.defenderShipId = defenderShipId;
    }
}
