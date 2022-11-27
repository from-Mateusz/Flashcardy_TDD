package cz.mateusz.flashcardy.tdd.player.dto;

public class PlayerRegistration {

    private ReadProperty name;

    private ReadProperty email;

    private ReadProperty password;

    public PlayerRegistration() {}

    public PlayerRegistration(ReadProperty name, ReadProperty email, ReadProperty password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public ReadProperty getName() {
        return name;
    }

    public void setName(ReadProperty name) {
        this.name = name;
    }

    public ReadProperty getEmail() {
        return email;
    }

    public void setEmail(ReadProperty email) {
        this.email = email;
    }

    public ReadProperty getPassword() {
        return password;
    }

    public void setPassword(ReadProperty password) {
        this.password = password;
    }
}
