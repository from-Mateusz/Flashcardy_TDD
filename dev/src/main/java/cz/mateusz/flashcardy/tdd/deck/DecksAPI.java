package cz.mateusz.flashcardy.tdd.deck;

import cz.mateusz.flashcardy.tdd.flashcard.Flashcard;

import java.util.List;

public class DecksAPI {

    private DeckService deckService;

    public DecksAPI(DeckService deckService) {
        this.deckService = deckService;
    }

    public Deck createEmptyDeck(String name) {
        Deck deck = new Deck(name);
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
}
