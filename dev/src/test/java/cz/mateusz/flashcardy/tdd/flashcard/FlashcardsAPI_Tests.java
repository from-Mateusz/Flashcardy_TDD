package cz.mateusz.flashcardy.tdd.flashcard;

import cz.mateusz.flashcardy.tdd.api.FlashcardsAPI;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.Matchers.*;

public class FlashcardsAPI_Tests {

    @Test
    public void givenSearchRequestForOtherPeopleReadyMadeFlashcardsThenReturnAllFlashcardsButPrivate() {
        FlashcardsAPI api = new FlashcardsAPI();
        List<Flashcard> flashcards = api.sendOtherPeopleReadyMadeFlashcards();
        final int flashcardsSize = flashcards.size();
        assertThat(flashcards.stream().filter(flashcard -> !(flashcard.isPrivate())).collect(Collectors.toList()),
                hasSize(flashcardsSize));
    }
}
