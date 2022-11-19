package cz.mateusz.flashcardy.tdd.flashcard;

public interface DeckSeekOperations {
    Deck seekDeckById(Long id) throws DeckNotFoundException;
}
