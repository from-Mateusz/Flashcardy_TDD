package cz.mateusz.flashcardy.tdd.player;

import cz.mateusz.flashcardy.tdd.player.constraints.EmailAddressConstraints;
import cz.mateusz.flashcardy.tdd.player.dto.PlayerRegistration;
import cz.mateusz.flashcardy.tdd.player.dto.PlayerRegistrationConfirmation;
import cz.mateusz.flashcardy.tdd.player.dto.PlayerRegistrationValidator;
import cz.mateusz.flashcardy.tdd.player.dto.ReadProperty;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.hasSize;

public class PlayerAPI_Tests {

    private final Long ZERO = 0L;

    private PlayerAPI api;

    @BeforeEach
    public void doBeforeAnyTest() {
        api = new PlayerAPI(new PlayerRegistrationValidator(
                new EmailAddressConstraints()
        ));
    }

    @Test
    public void registerPlayerForgettingAboutEmailAddressThenExpectValidationsToFail() {
        PlayerRegistration registration = new PlayerRegistration();
        PlayerRegistrationConfirmation registrationConfirmation =
                api.registerPlayer(createPlayerRegistrationWithoutEmailPassword());
        assertThat(registrationConfirmation.getValidations()
                        .stream()
                        .filter(validation -> validation.failed())
                        .count(),
                greaterThan(ZERO));
    }

    @Test
    public void registerPlayersWithCorrectDataThenCreatePlayersProfiles() {
        PlayerRegistration[] registrations = createPlayerRegistrations();

        final int FIRST_REGISTRATION = 0;

        final int SECOND_REGISTRATION = 1;

        PlayerRegistrationConfirmation firstRegistrationConfirmation = api.registerPlayer(
                registrations[FIRST_REGISTRATION]
        );

        PlayerRegistrationConfirmation secondRegistrationConfirmation = api.registerPlayer(
                registrations[SECOND_REGISTRATION]
        );

        assertThat(firstRegistrationConfirmation.getValidations()
                    .stream()
                    .filter(validation -> validation.success())
                    .count(),
                    greaterThan(0L));

        assertThat(secondRegistrationConfirmation.getValidations()
                        .stream()
                        .filter(validation -> validation.success())
                        .count(),
                    greaterThan(0L));
    }

    private PlayerRegistration createPlayerRegistrationWithoutEmailPassword() {
        final ReadProperty PLAYER_NAME = new ReadProperty("player_name", "Mateusz");
        final ReadProperty NO_EMAIL_ADDRESS = new ReadProperty("player_email_address", null);
        final ReadProperty NO_PASSWORD = new ReadProperty("player_password", null);
        return new PlayerRegistration(PLAYER_NAME, NO_EMAIL_ADDRESS, NO_PASSWORD);
    }

    private EmailAddress createInvalidEmailAddress() {
        return EmailAddress.of("hello.world" );
    }

    private PlayerRegistration[] createPlayerRegistrations() {
        PlayerRegistration[] registrations = new PlayerRegistration[2];
        final int FIRST_REGISTRATION = 0;
        final int SECOND_REGISTRATION = 1;

        registrations[FIRST_REGISTRATION] = new PlayerRegistration(
                new ReadProperty("player_name", "Mateusz"),
                new ReadProperty("player_email_address", "mateusz@mateusz.cz"),
                new ReadProperty("player_password", "lisa20Czyz!3")
        );

        registrations[SECOND_REGISTRATION] = new PlayerRegistration(
                new ReadProperty("player_name", "Lisa"),
                new ReadProperty("player_email_address", "lisa@mateusz.cz"),
                new ReadProperty("player_password", "Lisa20Czyz!3")
        );

        return registrations;
    }
}
