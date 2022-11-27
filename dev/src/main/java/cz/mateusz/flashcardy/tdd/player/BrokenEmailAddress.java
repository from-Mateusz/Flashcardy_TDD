package cz.mateusz.flashcardy.tdd.player;

public class BrokenEmailAddress extends RuntimeException {

    private String emailAddress;

    public BrokenEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getEmailAddress() {
        return emailAddress;
    }
}
