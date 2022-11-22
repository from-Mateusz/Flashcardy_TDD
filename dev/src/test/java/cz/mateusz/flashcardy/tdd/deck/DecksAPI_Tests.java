package cz.mateusz.flashcardy.tdd.deck;

import cz.mateusz.flashcardy.tdd.flashcard.Flashcard;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class DecksAPI_Tests {

    private DeckService deckService;

    private DeckRepository deckRepository;

    private DecksAPI api;

    private final Long RANDOM_DECK_ID = Math.round(Math.random() * 100);

    @BeforeEach
    public void doBeforeAnyTest() {
        deckRepository = mock(DeckRepository.class);
        deckService = new DeckService(deckRepository);
        api = new DecksAPI(deckService);
    }

    @Test
    public void createEmptyDeckWithGivenName() {
        final Deck deck = api.createEmptyDeck("Spanish - Food Nouns");
        assertThat(deck.getName(), equalTo("Spanish - Food Nouns"));
    }

    @Test
    public void whenPopulateDeckThenDeckGrowsAccordingly() {
        final List<Flashcard> flashcards = List.of( new Flashcard("como esta?", "how are you?"),
                                                    new Flashcard("esta manana", "this morning"));

        when(deckRepository.findDeckById(RANDOM_DECK_ID))
                .thenReturn(Optional.ofNullable(new Deck("Spanish - Basics vol. 1")));

        final Deck populatedDeck = api.populateDeck(RANDOM_DECK_ID, flashcards);

        assertThat(populatedDeck.getCards(),
                    contains(copyFlashcard(flashcards.get(0)), copyFlashcard(flashcards.get(1))));
    }

    @Test
    public void whenDepopulateDeckThenDeckShrinksAccordingly() {
        final List<Flashcard> flashcards = List.of( new Flashcard("como esta?", "how are you?"),
                                                    new Flashcard("esta manana", "this morning"));

        final Deck deck = api.createEmptyDeck("Spanish Basics vol.1" );

        when(deckRepository.findDeckById(RANDOM_DECK_ID))
                .thenReturn(Optional.ofNullable(deck));

        final Deck expandedDeck = api.populateDeck(RANDOM_DECK_ID, flashcards);

        final Deck shrunkDeck = api.depopulateDeck(RANDOM_DECK_ID, flashcards.subList(0, 1));

        assertThat(shrunkDeck.getCards(), not(contains(flashcards.subList(0, 1))));
    }

    /**
     * ======================================================================================
     *                                  HELPFUL METHODS
     * ======================================================================================
     */

    private Flashcard copyFlashcard(Flashcard flashcard) {
        return new Flashcard(flashcard.getNotion(), flashcard.getDefinition());
    }

    private List<Flashcard> createFlashcards() {
        return List.of( new Flashcard("como esta?", "how are you?"),
                        new Flashcard("esta manana", "this morning"),
                        new Flashcard( "donde?", "where?"),
                        new Flashcard("pero", "but"));
    }
}
