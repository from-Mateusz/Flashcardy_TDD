package cz.mateusz.flashcardy.tdd.player;

public class InvalidEmailAddress extends RuntimeException {

    private String emailAddress;

    public InvalidEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }
}
