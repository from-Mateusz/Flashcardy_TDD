package cz.mateusz.flashcardy.tdd.deck;

public class DeckNotFoundException extends RuntimeException {
    private Long id;

    public DeckNotFoundException(Long id) {
        this.id = id;
    }
}
