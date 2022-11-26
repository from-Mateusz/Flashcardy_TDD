package cz.mateusz.flashcardy.tdd.deck;

import cz.mateusz.flashcardy.tdd.player.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class DeckSharingAPI_Tests {

    private DeckSharingAPI api;

    private DeckSharingRepository repository;

    private DeckSharingService service;

    @BeforeEach
    public void doBeforeAnyTest() {
        repository = mock(DeckSharingRepository.class);
        service = new DeckSharingService(repository);
        api = new DeckSharingAPI(service);
    }

    @Test
    public void whenDeckSharingIsOnThenDeckIsFreelyAvailableToEveryPlayer() {
        Deck deck = new Deck("English Transport Vocabulary Review", createPlayerMateusz());

        DeckSharing sharing = api.shareDeck(deck);

        when(repository.findSharedDecks()).thenReturn(List.of(copyDeckSharing(sharing)));

        List<DeckSharing> sharings = api.getDecksSharedByOthers();

        assertThat(sharings, contains(sharing));
    }

    private Player createPlayerMateusz() {
        return new Player("Mateusz");
    }

    private Player createPlayerAndrzej() {
        return new Player("Andrzej");
    }

    private DeckSharing copyDeckSharing(DeckSharing sharing) {
        return new DeckSharing(sharing.getDeck());
    }
}
