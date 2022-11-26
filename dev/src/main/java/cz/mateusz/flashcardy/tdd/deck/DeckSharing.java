package cz.mateusz.flashcardy.tdd.deck;

import java.util.Objects;

public class DeckSharing {
    private Deck deck;

    public DeckSharing(Deck deck) {
        this.deck = deck;
    }

    public Deck getDeck() {
        return deck;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DeckSharing that = (DeckSharing) o;
        return Objects.equals(deck, that.deck);
    }

    @Override
    public int hashCode() {
        return Objects.hash(deck);
    }
}
