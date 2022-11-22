package cz.mateusz.flashcardy.tdd.flashcard;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.*;

public class FlashcardsAPI_Tests {

    private FlashcardsAPI api;

    @BeforeEach
    public void doBeforeAnyTest() {
        api = new FlashcardsAPI();
    }

    @Test
    public void whenShuffleFlashcardsThenProvideVerySameFlashcardsButDifferentlyOrdered() {
        List<Flashcard> receivedFlashcards = createFlashcards();
        List<Flashcard> shuffledFlashcards = api.shuffle(receivedFlashcards);
        System.out.println(receivedFlashcards);
        System.out.println(shuffledFlashcards);
        assertThat(receivedFlashcards, containsInAnyOrder(shuffledFlashcards.stream().toArray()));
        // Digress: Two lists are known to be equal if they are of the same length, have same elements and are equally ordered
        assertThat(receivedFlashcards, not(equalTo(shuffledFlashcards)));
    }

    private List<Flashcard> createFlashcards() {
        return List.of( new Flashcard("como esta?", "how are you?"),
                        new Flashcard("esta manana", "this morning"),
                        new Flashcard( "donde?", "where?"),
                        new Flashcard("pero", "but"));
    }
}
