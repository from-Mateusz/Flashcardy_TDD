package cz.mateusz.flashcardy.tdd.deck;

import java.util.List;

public class DeckSharingService {

    private DeckSharingRepository repository;

    public DeckSharingService(DeckSharingRepository repository) {
        this.repository = repository;
    }

    public List<DeckSharing> seekDecksSharedByOtherPlayers() {
        return repository.findSharedDecks();
    }
}
