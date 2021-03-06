package com.example.octoberexambattleships.util.validation;

import com.example.octoberexambattleships.service.ShipService;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueShipNameValidator implements ConstraintValidator<UniqueShipName, String> {

    private final ShipService shipService;

    public UniqueShipNameValidator(ShipService shipService) {
        this.shipService = shipService;
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return this.shipService.findByShipName(value);
    }
}
