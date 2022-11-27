package cz.mateusz.flashcardy.tdd.player;

import java.util.Objects;

public class Player {

    private String name;

    private EmailAddress emailAddress;

    private Password password;

    public Player(String name) {
        this.name = name;
    }

    public Player(String name, EmailAddress emailAddress) {
        this(name);
        this.changeEmailAddress(emailAddress);
    }

    public Player(String name, EmailAddress emailAddress, Password password) {
        this(name, emailAddress);
        changePassword(password);
    }

    public String getName() {
        return name;
    }

    public void changeEmailAddress(EmailAddress emailAddress) {
        this.emailAddress = emailAddress;
    }

    public void changePassword(Password password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return Objects.equals(name, player.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return "Player{" +
                "name='" + name + '\'' +
                '}';
    }
}
