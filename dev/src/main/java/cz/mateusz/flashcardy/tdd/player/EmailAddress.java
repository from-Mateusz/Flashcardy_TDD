package cz.mateusz.flashcardy.tdd.player;

import static cz.mateusz.flashcardy.tdd.player.constraints.EmailAddressConstraints.EmailAddressPatternConstraint;

public class EmailAddress {

    private static final EmailAddressPatternConstraint EMAIL_ADDRESS_PATTERN_CONSTRAINT = new EmailAddressPatternConstraint();

    private String value;

    private EmailAddress(String value) {
        if(EMAIL_ADDRESS_PATTERN_CONSTRAINT.isViolatedBy(value)) {
            throw new BrokenEmailAddress(value);
        }
        this.value = value;
    }

    public static EmailAddress of(String value) {
        return new EmailAddress(value);
    }


    public String getValue() {
        return value;
    }
}
