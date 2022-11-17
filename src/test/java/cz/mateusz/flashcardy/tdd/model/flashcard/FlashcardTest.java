package cz.mateusz.flashcardy.tdd.model.flashcard;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class FlashcardTest {

    @Test
    public void givenNullNotionThenThrowRuntimeException() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            Flashcard flashcard = new Flashcard(null, "definition");
        });
        String expectedMessage = "Notion must be provided!";
        String receivedMessage = exception.getMessage();
        assertThat(expectedMessage, equalTo(receivedMessage));
    }

    @Test
    public void givenBlankNotionThenThrowRuntimeException() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            Flashcard flashcard = new Flashcard("", "definition");
        });
        String expectedMessage = "Notion must not be blank!";
        String receivedMessage = exception.getMessage();
        assertThat(expectedMessage, equalTo(receivedMessage));
    }

    @Test
    public void givenNullDefinitionThenThrowRuntimeException() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
           Flashcard flashcard = new Flashcard("notion", null);
        });
        String expectedMessage = "Definition must be provided!";
        String receivedMessage = exception.getMessage();
        assertThat(expectedMessage, equalTo(receivedMessage));
    }

    @Test
    public void givenBlankDefinitionThenThrowRuntimeException() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            Flashcard flashcard = new Flashcard("notion", "");
        });
        String expectedMessage = "Definition must not be blank!";
        String receivedMessage = exception.getMessage();
        assertThat(expectedMessage, equalTo(receivedMessage));
    }

    @Test
    public void givenOneNotionUsageExampleThenFlashcardStoresThisOne() {
        Flashcard flashcard = new Flashcard("Como esta?", "How are you?");
        String notionUsageExample = "Come esta Maria?";
        flashcard.addNotionUsageExample(notionUsageExample);
        assertThat(flashcard.getNotionUsageExamples(), contains("Come esta Maria?"));
    }

    @Test
    public void givenTwoDifferentUsageExamplesThenFlashcardStoresTheseTwo() {
        Flashcard flashcard = new Flashcard("to purvey", "dostarczyć, zaopatrzyć");
        String firstNotionUsageExample = "People should purvey their passports until the may has ended";
        String secondNotionUsageExample = "I can purvey a lot of clothes to you";
        flashcard.addManyNotionUsageExamples(firstNotionUsageExample, secondNotionUsageExample);
        assertThat(flashcard.getNotionUsageExamples(),
                contains("People should purvey their passports until the may has ended", "I can purvey a lot of clothes to you"));
    }

    @Test
    public void givenNullUsageExampleThenFlashcardUsageExamplesRemainIntact() {
        Flashcard flashcard = new Flashcard("to purvey", "dostarczyć, zaopatrzyć");
        String nullNotionUsageExample = null;
        flashcard.addNotionUsageExample(nullNotionUsageExample);
        assertThat(flashcard.getNotionUsageExamples(), hasSize(0));
    }

    @Test
    public void givenBlankUsageExampleThenFlashcardUsageExamplesRemainIntact() {
        Flashcard flashcard = new Flashcard("to purvey", "dostarczyć, zaopatrzyć");
        String blankNotionUsageExample = "  ";
        flashcard.addNotionUsageExample(blankNotionUsageExample);
        assertThat(flashcard.getNotionUsageExamples(), hasSize(0));
    }
}
