package cz.mateusz.flashcardy.tdd.flashcard;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.List;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;

public class FlashcardsAPI_Tests {

    private DeckService deckService;

    private DeckRepository deckRepository;

    @BeforeEach
    public void doBeforeAnyTest() {
        deckRepository = mock(DeckRepository.class);
        deckService = new DeckService(deckRepository);
    }

    @Test
    public void createEmptyDeckWithGivenName() {
        final FlashcardsAPI api = new FlashcardsAPI(deckService);
        final Deck deck = api.createEmptyDeck("Spanish - Food Nouns");
        assertThat(deck.getName(), equalTo("Spanish - Food Nouns"));
    }

    @Test
    public void fillDeckWithFlashcards() {
        final FlashcardsAPI api = new FlashcardsAPI(deckService);
        final List<Flashcard> flashcards = List.of( new Flashcard("como esta?", "how are you?"),
                                                    new Flashcard("esta manana", "this morning"));
        final Long deckId = 1L;

        when(deckRepository.findDeckById(1L))
                .thenReturn(Optional.ofNullable(new Deck("Spanish - Basics vol. 1")));

        final boolean deckFilled = api.fillDeck(deckId, flashcards);
        assertThat(deckFilled, is(true));
    }
}
