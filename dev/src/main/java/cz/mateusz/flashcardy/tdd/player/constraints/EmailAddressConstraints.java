package cz.mateusz.flashcardy.tdd.player.constraints;

import java.util.regex.Pattern;

public class EmailAddressConstraints implements Constraints {

    private boolean violated;

    private Constraint[] constraints;

    private boolean[] violatedConstraints;

    public EmailAddressConstraints() {
        constraints = new Constraint[] {
            new EmailAddressPatternConstraint()
        };

        violatedConstraints = new boolean[] {
                false,
        };
    }

    public EmailAddressConstraints syntacticallyCorrect(String email) {
        EmailAddressPatternConstraint constraint = (EmailAddressPatternConstraint) constraints[ConstraintName.SYNTAX_CONSTRAINT.ordinal()];
        violated = constraint.isViolatedBy(email);
        if(violated) {
            this.violatedConstraints[ConstraintName.SYNTAX_CONSTRAINT.ordinal()] = true;
        }
        return this;
    }

    public boolean areViolated() {
        return violated;
    }

    @Override
    public void resetViolations() {
        violated = false;
    }

    public boolean isViolated(ConstraintName name) {
        return violatedConstraints[name.ordinal()];
    }

    public enum ConstraintName {
        SYNTAX_CONSTRAINT;
    }

    public static class EmailAddressPatternConstraint implements Constraint<String> {

        private static final Pattern VALID_EMAIL_PATTERN = Pattern.compile("\\b[a-z0-9]+[-.]?[a-z0-9]+@{1}[a-z0-9]+\\.{1}[a-z]{2,3}\\b");

        @Override
        public boolean isViolatedBy(String emailAddress) {
            return not(emailAddress != null) || not(VALID_EMAIL_PATTERN.matcher(emailAddress).matches());
        }

        private boolean not(boolean value) {
            return !value;
        }
    }
}
