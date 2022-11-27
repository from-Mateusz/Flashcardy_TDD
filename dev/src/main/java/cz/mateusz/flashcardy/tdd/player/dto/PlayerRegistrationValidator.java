package cz.mateusz.flashcardy.tdd.player.dto;

import cz.mateusz.flashcardy.tdd.player.constraints.EmailAddressConstraints;

import java.util.ArrayList;
import java.util.List;

public class PlayerRegistrationValidator {

    private EmailAddressConstraints emailAddressConstraints;

    public PlayerRegistrationValidator(EmailAddressConstraints emailAddressConstraints) {
        this.emailAddressConstraints = emailAddressConstraints;
    }

    public List<Validation> validate(PlayerRegistration registration) {
        List<Validation> validations = new ArrayList<>();
        validations.add( emailAddressIsNotBroken(registration.getEmail()) );
        return validations;
    }

    private Validation emailAddressIsNotBroken(ReadProperty emailAddress) {
        String readEmailAddress = (String) emailAddress.getValue();
        emailAddressConstraints.resetViolations();
        emailAddressConstraints.syntacticallyCorrect(readEmailAddress);
        if(emailAddressConstraints.isViolated(EmailAddressConstraints.ConstraintName.SYNTAX_CONSTRAINT)) {
            return new Validation(new Validation.ValidationBuilder(emailAddress)
                    .withError("Please provide correct email address, thanks!"));
        }
        return new Validation(new Validation.ValidationBuilder(emailAddress));
    }

    public static class Validation {

        private static final String EMPTY_SUMMARY = "";

        private ReadProperty property;

        private List<String> errors;

        private Validation() {}

        public Validation(ValidationBuilder builder) {
            this.property = builder.getProperty();
            this.errors = builder.getErrors();
        }

        public boolean success() {
            return !failed();
        }

        public boolean failed() {
            return !errors.isEmpty();
        }

        public static class ValidationBuilder {

            private ReadProperty property;

            private List<String> errors = new ArrayList<>();

            public ValidationBuilder(ReadProperty property) {
                this.property = property;
            }

            public ReadProperty getProperty() {
                return property;
            }

            public List<String> getErrors() {
                return errors;
            }

            public ValidationBuilder withError(String error) {
                errors.add(error);
                return this;
            }
        }
    }
}
