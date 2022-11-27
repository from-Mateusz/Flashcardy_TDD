package cz.mateusz.flashcardy.tdd.player;

public class BrokenPassword extends RuntimeException {

    private Password password;

    public BrokenPassword(Password password) {
        this.password = password;
    }

    public Password getPassword() {
        return password;
    }
}
