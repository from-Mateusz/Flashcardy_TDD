package cz.mateusz.flashcardy.tdd.player;

import cz.mateusz.flashcardy.tdd.player.constraints.EmailAddressConstraints;
import cz.mateusz.flashcardy.tdd.player.dto.PlayerRegistration;
import cz.mateusz.flashcardy.tdd.player.dto.PlayerRegistrationValidator;
import cz.mateusz.flashcardy.tdd.player.dto.ReadProperty;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

public class PlayerAPI_Tests {

    private final ReadProperty NO_EMAIL_ADDRESS = new ReadProperty("player_email_address", null);

    private final ReadProperty NO_PASSWORD = new ReadProperty("player_password", null);

    private final ReadProperty PLAYER_NAME = new ReadProperty("player_name", "Mateusz");

    private final Long ZERO = 0L;

    private PlayerAPI api;

    @BeforeEach
    public void doBeforeAnyTest() {
        api = new PlayerAPI(new PlayerRegistrationValidator(
                new EmailAddressConstraints()
        ));
    }

    @Test
    public void registerPlayerForgettingAboutEmailAddressThanExpectValidationsToFail() {
        PlayerRegistration registration = new PlayerRegistration();
        List<PlayerRegistrationValidator.Validation> validations =
                api.registerPlayer(createPlayerRegistrationWithoutEmailPassword());
        assertThat(validations.stream().filter(validation -> validation.failed() == true).count(),
                greaterThan(ZERO));
    }

    private PlayerRegistration createPlayerRegistrationWithoutEmailPassword() {
        return new PlayerRegistration(PLAYER_NAME, NO_EMAIL_ADDRESS, NO_PASSWORD);
    }

    private EmailAddress createInvalidEmailAddress() {
        return EmailAddress.of("hello.world" );
    }
}
