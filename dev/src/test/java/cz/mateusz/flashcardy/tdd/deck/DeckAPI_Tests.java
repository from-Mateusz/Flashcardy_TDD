package cz.mateusz.flashcardy.tdd.deck;

import cz.mateusz.flashcardy.tdd.flashcard.Flashcard;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class DeckAPI_Tests {

    private DeckService deckService;

    private DeckRepository deckRepository;

    private DeckAPI api;

    @BeforeEach
    public void doBeforeAnyTest() {
        deckRepository = mock(DeckRepository.class);
        deckService = new DeckService(deckRepository);
        api = new DeckAPI(deckService);
    }

    @Test
    public void createEmptyDeckWithGivenName() {
        final Deck deck = api.createEmptyDeck("Spanish - Food Nouns");
        assertThat(deck.getName(), equalTo("Spanish - Food Nouns"));
    }

    @Test
    public void expandDeckWithFlashcards() {
        final List<Flashcard> flashcards = List.of( new Flashcard("como esta?", "how are you?"),
                                                    new Flashcard("esta manana", "this morning"));
        final Long deckId = 1L;

        when(deckRepository.findDeckById(1L))
                .thenReturn(Optional.ofNullable(new Deck("Spanish - Basics vol. 1")));

        final Deck expandedDeck = api.expandDeckWithFlashcards(deckId, flashcards);
        assertThat(expandedDeck.getCards(),contains(copyFlashcard(flashcards.get(0)),
                                                    copyFlashcard(flashcards.get(1))));
    }

    /**
     * ======================================================================================
     *                                  HELPER METHODS
     * ======================================================================================
     */

    private Flashcard copyFlashcard(Flashcard flashcard) {
        return new Flashcard(flashcard.getNotion(), flashcard.getDefinition());
    }
}
