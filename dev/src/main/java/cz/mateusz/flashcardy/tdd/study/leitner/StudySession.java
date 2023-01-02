package cz.mateusz.flashcardy.tdd.study.leitner;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class StudySession {

    private LocalDateTime nextDate;

    private final List<Flashcard> cards;

    private Flashcard currentlyStudiedCard;

    public StudySession(List<Flashcard> cards,
                        StudyInterval interval) {
        this.cards = cards;
        nextDate = LocalDateTime.now()
                                .plusSeconds(interval.inSeconds());
    }

    public Optional<Flashcard> study() {
        final Flashcard[] cardsStillToAttempt = remaining();
        if(cardsStillToAttempt.length == 0) {
            currentlyStudiedCard = null;
            return Optional.ofNullable(null);
        }
        int randomCardPosition = (int) Math.ceil(Math.random() * (cardsStillToAttempt.length - 1));
        currentlyStudiedCard = cards.remove(randomCardPosition);
        if( currentlyStudiedCard.getAttemptDate() == null || deadline().isAfter(currentlyStudiedCard.getAttemptDate()) ) {
            return Optional.ofNullable(currentlyStudiedCard);
        }
        else {
            cards.add(currentlyStudiedCard);
            return study();
        }
    }
    
    public Flashcard getCurrentlyStudiedCard() {
        return currentlyStudiedCard;
    }

    public void keep(Flashcard card) {
        this.cards.add(card);
    }

    private Flashcard[] remaining() {
        return cards.stream()
                    .filter(card -> card.getAttemptDate() == null || deadline().isAfter(card.getAttemptDate()))
                    .toArray(Flashcard[]::new);
    }

    public LocalDateTime deadline() {
        return this.nextDate;
    }
}
