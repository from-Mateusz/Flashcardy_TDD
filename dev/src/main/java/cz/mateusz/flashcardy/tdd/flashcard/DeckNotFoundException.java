package cz.mateusz.flashcardy.tdd.flashcard;

public class DeckNotFoundException extends RuntimeException {
    private Long id;

    public DeckNotFoundException(Long id) {
        this.id = id;
    }
}
