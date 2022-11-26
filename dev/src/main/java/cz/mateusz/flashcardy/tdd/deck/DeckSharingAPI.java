package cz.mateusz.flashcardy.tdd.deck;

import java.util.Collections;
import java.util.List;

public class DeckSharingAPI {

    private DeckSharingService service;

    public DeckSharingAPI(DeckSharingService service) {
        this.service = service;
    }

    public DeckSharing shareDeck(Deck deck) {
        return new DeckSharing(deck);
    }

    public List<DeckSharing> getDecksSharedByOthers() {
        return service.seekDecksSharedByOtherPlayers();
    }
}
