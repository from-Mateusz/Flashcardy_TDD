package cz.mateusz.flashcardy.tdd.deck;

import java.util.Optional;

public interface DeckRepository {
    Optional<Deck> findDeckById(Long id);
}
