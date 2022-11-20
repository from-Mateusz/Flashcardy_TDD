package cz.mateusz.flashcardy.tdd.deck;

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
    public Deck refresh(Deck deck) {
        return null;
    }
}
