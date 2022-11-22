package cz.mateusz.flashcardy.tdd.flashcard;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class FlashcardsAPI {

    public List<Flashcard> shuffle(List<Flashcard> flashcards) {
        List<Flashcard> copiedFlashcards = flashcards.stream()
                                                    .map(card -> new Flashcard(card.getNotion(), card.getDefinition()))
                                                    .collect(Collectors.toList());
        Collections.shuffle(copiedFlashcards);
        return copiedFlashcards;
    }
}
