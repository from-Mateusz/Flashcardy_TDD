package cz.mateusz.flashcardy.tdd.deck;

import java.util.List;
import java.util.Optional;

public interface DeckRepository {
    Optional<Deck> findDeckById(Long id);

    List<Deck> findDecksWithSharedContent();
}
