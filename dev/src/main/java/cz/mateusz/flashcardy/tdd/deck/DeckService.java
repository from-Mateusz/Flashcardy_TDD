package cz.mateusz.flashcardy.tdd.deck;

import cz.mateusz.flashcardy.tdd.flashcard.Flashcard;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class DeckService implements DeckSeekOperations, DeckManipulationOperations {

    private DeckRepository deckRepository;

    public DeckService(DeckRepository deckRepository) {
        this.deckRepository = deckRepository;
    }

    @Override
    public Deck seekDeckById(Long id) throws DeckNotFoundException {
        Optional<Deck> presumablyDeck = deckRepository.findDeckById(id);

        if(!presumablyDeck.isPresent()) {
            throw new DeckNotFoundException(id);
        }

        return presumablyDeck.get();
    }

    @Override
    public Deck update(Deck deck) {
        return null;
    }
}
