package cz.mateusz.flashcardy.tdd.player.constraints;

public interface Constraints {
    boolean areViolated();

    void resetViolations();
}
