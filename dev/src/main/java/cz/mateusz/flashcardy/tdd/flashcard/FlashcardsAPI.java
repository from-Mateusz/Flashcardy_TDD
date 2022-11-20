package cz.mateusz.flashcardy.tdd.flashcard;

import cz.mateusz.flashcardy.tdd.deck.Deck;
import cz.mateusz.flashcardy.tdd.deck.DeckService;

import java.util.List;

public class FlashcardsAPI {

    private DeckService deckService;

    public FlashcardsAPI(DeckService deckService) {
        this.deckService = deckService;
    }

    Deck createEmptyDeck(String name) {
        return new Deck(name);
    }

    Boolean fillDeck(Long deckId, List<Flashcard> flashcards) {
        Deck deck = deckService.seekDeckById(deckId);
//        return deck.fill(flashcards);
        return true;
    }
}
