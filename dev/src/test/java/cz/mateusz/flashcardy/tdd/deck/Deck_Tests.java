package cz.mateusz.flashcardy.tdd.deck;

import cz.mateusz.flashcardy.tdd.flashcard.Flashcard;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class Deck_Tests {

    @Test
    public void CreateDeckWithoutAnyNameThanExpectError() {
        IllegalArgumentException nullDeckNameException = assertThrows(IllegalArgumentException.class, () -> {
            Deck deck = new Deck(null);
        });
        assertThat(nullDeckNameException.getMessage(), equalTo("Deck without a name cannot exist"));
    }

    @Test
    public void CreateDeckWithBlankNameThanExpectError() {
        IllegalArgumentException blankDeckNameException = assertThrows(IllegalArgumentException.class, () -> {
            Deck deck = new Deck(" ");
        });
        assertThat(blankDeckNameException.getMessage(), equalTo("Deck with a blank name cannot exist"));
    }

    @Test
    public void WhenDeckExpandsWithGivenFlashcardsThanItKeepsThoseFlashcards() {
        Deck deck = new Deck("Spanish Basics vol.1 ");
        Flashcard flashcard1 = new Flashcard("come esta?", "how are you?");
        Flashcard flashcard2 = new Flashcard("la casa", "house");
        deck.expandWith(flashcard1);
        deck.expandWith(flashcard2);
        assertThat(deck.getCards(), contains(
                    copyFlashcard(flashcard1),
                    copyFlashcard(flashcard2)
                ));
    }

    /**
     * ======================================================================================
     *                                  HELPFUL METHODS
     * ======================================================================================
     */
    private Flashcard copyFlashcard(Flashcard flashcard) {
        return new Flashcard(flashcard.getNotion(), flashcard.getDefinition());
    }
}
