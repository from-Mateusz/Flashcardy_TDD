package cz.mateusz.flashcardy.tdd.deck;

import cz.mateusz.flashcardy.tdd.flashcard.Flashcard;
import cz.mateusz.flashcardy.tdd.player.Player;
import cz.mateusz.flashcardy.tdd.security.WhoIsAuthenticatedService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.*;

public class DecksAPI_Tests {

    private DeckService deckService;

    private DeckRepository deckRepository;

    private DecksAPI api;

    private final Long RANDOM_DECK_ID = Math.round(Math.random() * 100);

    @BeforeEach
    public void doBeforeAnyTest() {
        deckRepository = mock(DeckRepository.class);
        deckService = new DeckService(deckRepository, whoIsAuthenticatedServiceMock());
        api = new DecksAPI(deckService);

    }

    private WhoIsAuthenticatedService whoIsAuthenticatedServiceMock() {
        WhoIsAuthenticatedService whoIsAuthenticatedService = mock(WhoIsAuthenticatedService.class);
        when(whoIsAuthenticatedService.tell()).thenReturn(Optional.ofNullable(createPlayerMateusz()));
        return whoIsAuthenticatedService;
    }

    @Test
    public void createEmptyDeckWithoutNameThanExpectError() {
        final IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> api.createEmptyDeck(null, createPlayerMateusz()));
        assertThat(exception.getMessage(), equalTo("Deck without a name cannot exist"));
    }

    @Test
    public void whenEmptyDeckIsInstantiatedThenItShouldHaveNameAndOwner() {
        final Deck deck = api.createEmptyDeck("Spanish - Food Nouns", new Player("Mateusz"));
        assertThat(deck.getName(), equalTo("Spanish - Food Nouns"));
        assertThat(deck.getOwner(), equalTo(copyDeckOwner(deck)));
    }

    @Test
    public void whenPopulateDeckThenDeckGrowsAccordingly() {
        final List<Flashcard> flashcards = createFlashcards();

        when(deckRepository.findDeckById(RANDOM_DECK_ID))
                .thenReturn(Optional.ofNullable(new Deck("Spanish - Basics vol. 1")));

        final Deck populatedDeck = api.populateDeck(RANDOM_DECK_ID, flashcards);

        System.out.println(populatedDeck.getCards());

        assertThat(populatedDeck.getCards(),
                    hasItems(copyFlashcard(flashcards.get(0)), copyFlashcard(flashcards.get(1))));
    }

    @Test
    public void whenDepopulateDeckThenDeckShrinksAccordingly() {
        final List<Flashcard> flashcards = createFlashcards();

        final Deck deck = api.createEmptyDeck("Spanish Basics vol.1", createPlayerMateusz() );

        when(deckRepository.findDeckById(RANDOM_DECK_ID))
                .thenReturn(Optional.ofNullable(deck));

        final Deck expandedDeck = api.populateDeck(RANDOM_DECK_ID, flashcards);

        final Deck shrunkDeck = api.depopulateDeck(RANDOM_DECK_ID, flashcards.subList(0, 1));

        assertThat(shrunkDeck.getCards(), not(contains(flashcards.subList(0, 1))));
    }

    @Test
    public void deckSharingMakesDeckFreelyAvailableToOtherPlayers() {
        final List<Flashcard> flashcards = createFlashcards();

        final Deck deck = api.createEmptyDeck("Spanish Basics vol.1", createPlayerMateusz());

        final DeckSharing deckSharing = api.shareDeckWithOthers(deck);

        final List<Deck> sharedDecks = api.findDecksSharedByOthers();

        assertThat(sharedDecks, contains(copyDeck(deck)));
    }

//    @Test
//    public void GivenDeckOfFlashcardHasSharedContent_WhenFindReadyMadeFlashcards_ThenIncludeItInResults() {
//        final List<Flashcard> flashcards = createFlashcards();
//
//        final Deck deck = api.createEmptyDeck("Shared Spanish Basics vol.1", createPlayerMateusz() );
//
//        deck.shareContentWithOthers();
//
//        when(deckRepository.findReadyMadeDeck())
//                .thenReturn(List.of(deck));
//
//        final List<Deck> otherPlayersDecks = api.getOtherPlayersDecks();
//
//        assertThat(otherPlayersDecks.stream().filter(d -> d.hasSharedContentWithOthers()).collect(Collectors.toList()),
//                    hasSize(1));
//    }

//    public void playerHasNoAccessToOtherPlayersDeckContentWhenItsContentHasBeenHiddenFromOthers() {
//        final List<Flashcard> flashcards = createFlashcards();
//
//        final Deck deck = api.createEmptyDeck("Shared Spanish Basics vol.1" );
//
//        deck.shareContentWithOthers();
//
////        deck.hideContentFromOthers();
//
//        when(deckRepository.findDecksWithSharedContent())
//                .thenReturn(List.of(deck));
//
//        final List<Deck> otherPlayersDecks = api.getOtherPlayersDecks();
//
//        assertThat(otherPlayersDecks.stream().filter(d -> d.hasSharedContentWithOthers()).collect(Collectors.toList()),
//                hasSize(otherPlayersDecks.size()));
//    }

    /**
     * ======================================================================================
     *                                  HELPFUL METHODS
     * ======================================================================================
     */

    private Deck copyDeck(Deck deck) {
        Deck copy = new Deck(deck.getName(), copyDeckOwner(deck));
        copy.expandBy(deck.getCards()
                .stream()
                .map(card -> copyFlashcard(card))
                .collect(Collectors.toList())
        );
        return copy;
    }

    private Flashcard copyFlashcard(Flashcard flashcard) {
        return new Flashcard(flashcard.getNotion(), flashcard.getDefinition());
    }

    private Player copyDeckOwner(Deck deck) {
        return new Player(deck.getOwner().getName());
    }

    private Player createPlayerMateusz() {
        Random random = new Random();
        return new Player("Mateusz");
    }

    private List<Flashcard> createFlashcards() {
        return List.of( new Flashcard("como esta?", "how are you?"),
                        new Flashcard("esta manana", "this morning"),
                        new Flashcard( "donde?", "where?"),
                        new Flashcard("pero", "but"));
    }
}
