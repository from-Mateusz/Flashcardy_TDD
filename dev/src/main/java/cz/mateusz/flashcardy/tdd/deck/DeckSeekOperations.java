package cz.mateusz.flashcardy.tdd.deck;

public interface DeckSeekOperations {
    Deck seekDeckById(Long id) throws DeckNotFoundException;
}
