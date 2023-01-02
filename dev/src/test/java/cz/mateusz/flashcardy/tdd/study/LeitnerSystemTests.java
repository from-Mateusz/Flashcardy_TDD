package cz.mateusz.flashcardy.tdd.study;

import cz.mateusz.flashcardy.tdd.study.leitner.Flashcard;
import cz.mateusz.flashcardy.tdd.study.leitner.LeitnerSystem;
import cz.mateusz.flashcardy.tdd.study.leitner.StudyInterval;
import cz.mateusz.flashcardy.tdd.study.leitner.StudySession;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

public class LeitnerSystemTests {

    @Test
    public void given_flashcards_populate_study_sessions_and_then_leitner_system() {
        final List<Flashcard> cards = new ArrayList<>();
        cards.add(new Flashcard("What city is a capital of a Poland?", "Warsaw"));
        cards.add(new Flashcard("What city is a capital of a Germany?", "Monachium"));
        StudySession session = new StudySession(cards, StudyInterval.days(2));
        LeitnerSystem system = new LeitnerSystem(new StudySession[]{ session });
        final Optional<Flashcard> possibleFlashcard = system.study();
        assertThat(possibleFlashcard.isPresent(), is(true));
    }

    @Test
    public void when_answered_all_questions_then_stop_giving_other_questions() {
        final List<Flashcard> cards = new ArrayList<>();
        cards.add(new Flashcard("What city is a capital of a Poland?", "Warsaw"));
        cards.add(new Flashcard("What city is a capital of a Germany?", "Monachium"));
        StudySession session = new StudySession(cards, StudyInterval.days(2));
        LeitnerSystem system = new LeitnerSystem(new StudySession[]{ session });
        system.study();
        system.answer(true);
        system.study();
        system.answer(true);

        Optional<Flashcard> possibleFlashcard = system.study();
        assertThat(possibleFlashcard.isPresent(), is(false));
    }
}
