package cz.mateusz.flashcardy.tdd.deck;

import java.util.List;

public interface DeckSeekOperations {
    Deck seekDeckById(Long id) throws DeckNotFoundException;

    List<Deck> seekDecksSharedByOthers();
}
