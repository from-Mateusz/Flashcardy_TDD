package cz.mateusz.flashcardy.tdd.deck;

import cz.mateusz.flashcardy.tdd.flashcard.Flashcard;

import java.util.List;

public class DeckAPI {

    private DeckService deckService;

    public DeckAPI(DeckService deckService) {
        this.deckService = deckService;
    }

    public Deck createEmptyDeck(String name) {
        Deck deck = new Deck(name);
        return deck;
    }

    public Deck expandDeckWithFlashcards(Long deckId, List<Flashcard> flashcards) {
        Deck deck = deckService.seekDeckById(deckId);
        deck.expandWith(flashcards);
        return deck;
    }
}
