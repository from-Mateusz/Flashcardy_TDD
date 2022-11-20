package cz.mateusz.flashcardy.tdd.flashcard;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.*;

public class Flashcard_Tests {

    @Test
    public void CreateFlashcardWithoutAnyNotionOrDefinitionThanExpectError() {
        IllegalArgumentException nullNotionFlashcard = assertThrows(IllegalArgumentException.class, () -> {
           Flashcard flashcard = new Flashcard(null, "definition");
        });
        IllegalArgumentException nullDefinitionFlashcard = assertThrows(IllegalArgumentException.class, () -> {
            Flashcard flashcard = new Flashcard( "notion", null );
        });
        assertThat(nullNotionFlashcard.getMessage(), equalTo("Flashcard needs notion"));
        assertThat(nullDefinitionFlashcard.getMessage(), equalTo("Flashcard needs definition"));
    }

    @Test
    public void CreateFlashcardWithBlankNotionOrDefinitionThanExpectError() {
        IllegalArgumentException nullNotionFlashcard = assertThrows(IllegalArgumentException.class, () -> {
            Flashcard flashcard = new Flashcard(" ", "definition");
        });
        IllegalArgumentException nullDefinitionFlashcard = assertThrows(IllegalArgumentException.class, () -> {
            Flashcard flashcard = new Flashcard( "notion", " " );
        });
        assertThat(nullNotionFlashcard.getMessage(), equalTo("Flashcard cannot have blank notion"));
        assertThat(nullDefinitionFlashcard.getMessage(), equalTo("Flashcard cannot have blank definition"));
    }
}
