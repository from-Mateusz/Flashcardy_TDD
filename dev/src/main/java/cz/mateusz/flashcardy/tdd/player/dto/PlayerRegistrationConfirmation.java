package cz.mateusz.flashcardy.tdd.player.dto;

import java.util.List;

import static cz.mateusz.flashcardy.tdd.player.dto.PlayerRegistrationValidator.*;

public class PlayerRegistrationConfirmation {

    private List<Validation> validations;

    public PlayerRegistrationConfirmation(List<Validation> validations) {
        this.validations = validations;
    }

    public List<Validation> getValidations() {
        return validations;
    }
}
