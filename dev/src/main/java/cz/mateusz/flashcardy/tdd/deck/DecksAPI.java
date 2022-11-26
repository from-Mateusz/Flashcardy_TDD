package cz.mateusz.flashcardy.tdd.deck;

import cz.mateusz.flashcardy.tdd.flashcard.Flashcard;
import cz.mateusz.flashcardy.tdd.player.Player;

import java.util.Collections;
import java.util.List;

public class DecksAPI {

    private DeckService deckService;

    public DecksAPI(DeckService deckService) {
        this.deckService = deckService;
    }

    public Deck createEmptyDeck(String name, Player owner) {
        Deck deck = new Deck(name, owner);
        return deck;
    }

    public Deck populateDeck(Long deckId, List<Flashcard> flashcards) {
        Deck deck = deckService.seekDeckById(deckId);
        deck.expandBy(flashcards);
        return deck;
    }

    public Deck depopulateDeck(Long deckId, List<Flashcard> flashcards) {
        Deck deck = deckService.seekDeckById(deckId);
        deck.shrinkBy(flashcards);
        return deck;
    }

    public DeckSharing shareDeckWithOthers(Deck deck) {
        return new DeckSharing(deck);
    }

    public List<Deck> findDecksSharedByOthers() {
        return Collections.emptyList();
    }
}
