package cz.mateusz.flashcardy.tdd.deck;

import cz.mateusz.flashcardy.tdd.flashcard.Flashcard;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {

    private Long id;

    private String name;

    private List<Flashcard> cards = new ArrayList<>();

    public Deck(String name) {
        setName(name);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        if(id != null) {
            throw new UnsupportedOperationException("Once id is set then cannot be changed");
        }
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if(name == null) {
            throw new IllegalArgumentException("Deck without a name cannot exist");
        }
        if(name.isBlank()) {
            throw new IllegalArgumentException("Deck with a blank name cannot exist");
        }
        this.name = name;
    }

    public boolean expandBy(Flashcard card) {
        return this.cards.add(card);
    }

    public boolean expandBy(List<Flashcard> cards) {
        return this.cards.addAll(cards);
    }

    public boolean shrinkBy(List<Flashcard> cards) {
        return this.cards.removeAll(cards);
    }

    public List<Flashcard> getCards() {
        return Collections.unmodifiableList(this.cards);
    }
}
