package cz.mateusz.flashcardy.tdd.deck;

import java.util.List;

public interface DeckSharingRepository {
    List<DeckSharing> findSharedDecks();
}
