package cz.mateusz.flashcardy.tdd.player;

import cz.mateusz.flashcardy.tdd.player.dto.PlayerRegistration;
import cz.mateusz.flashcardy.tdd.player.dto.PlayerRegistrationValidator;
import org.springframework.http.ResponseEntity;

import java.util.List;

public class PlayerAPI {

    private PlayerRegistrationValidator playerRegistrationValidator;

    public PlayerAPI(PlayerRegistrationValidator playerRegistrationValidator) {
        this.playerRegistrationValidator = playerRegistrationValidator;
    }

    public List<PlayerRegistrationValidator.Validation> registerPlayer(PlayerRegistration registration) {
        return playerRegistrationValidator.validate(registration);
    }
}
