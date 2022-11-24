package cz.mateusz.flashcardy.tdd.deck;

import cz.mateusz.flashcardy.tdd.player.Player;
import cz.mateusz.flashcardy.tdd.security.WhoIsAuthenticatedService;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class DeckService implements DeckSeekOperations, DeckManipulationOperations {

    private DeckRepository deckRepository;

    private WhoIsAuthenticatedService whoIsAuthenticated;

    public DeckService(DeckRepository deckRepository, WhoIsAuthenticatedService whoIsAuthenticated) {
        this.deckRepository = deckRepository;
        this.whoIsAuthenticated = whoIsAuthenticated;
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

    @Override
    public List<Deck> seekDecksSharedByOthers() {
        Optional<Player> possibleAuthenticatedPlayer = whoIsAuthenticated.tell();
        if(possibleAuthenticatedPlayer.isPresent()) {
            Player player = possibleAuthenticatedPlayer.get();
            return seekDecksSharedByOtherPlayersThan(player);
        }
        return Collections.emptyList();
    }

    private List<Deck> seekDecksSharedByOtherPlayersThan(Player player) {
        List<Deck> decks = deckRepository.findDecksWithSharedContent();
        return decks.stream()
                    .filter(deck -> player.equals(deck.getOwner()))
                    .collect(Collectors.toList());
    }
}
