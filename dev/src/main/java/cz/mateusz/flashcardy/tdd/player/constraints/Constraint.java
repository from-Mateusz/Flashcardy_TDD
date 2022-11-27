package cz.mateusz.flashcardy.tdd.player.constraints;

public interface Constraint<T> {
    boolean isViolatedBy(T value);
}
