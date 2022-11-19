package cz.mateusz.flashcardy.tdd.flashcard;

import java.util.Optional;

public interface DeckRepository {
    Optional<Deck> findDeckById(Long id);
}
