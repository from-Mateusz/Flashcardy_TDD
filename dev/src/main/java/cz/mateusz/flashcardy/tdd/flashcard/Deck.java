package cz.mateusz.flashcardy.tdd.flashcard;

import java.util.List;

public class Deck {

    private Long id;

    private String name;

    public Deck(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public boolean fill(Flashcard flashcard) {
        return true;
    }

    public boolean fill(List<Flashcard> flashcards) {
        return true;
    }
}
