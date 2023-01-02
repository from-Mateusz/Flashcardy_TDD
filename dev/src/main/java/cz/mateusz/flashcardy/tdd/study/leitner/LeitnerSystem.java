package cz.mateusz.flashcardy.tdd.study.leitner;

import java.util.Arrays;
import java.util.Optional;

public class LeitnerSystem {

    private final static int UNKNOWN_SESSION = -1;

    private StudySession[] sessions;

    private int session = UNKNOWN_SESSION;

    public LeitnerSystem(StudySession[] sessions) {
        this.sessions = Arrays.copyOf(sessions, sessions.length);
    }

    public Optional<Flashcard> study() {
        if(session == UNKNOWN_SESSION) {
            arrangeSessions();
            session = 0;
        }
        return sessions[session].study();
    }

    public void answer(boolean correctly) {
        Flashcard card = sessions[session].getCurrentlyStudiedCard();
        card.attempt(correctly);
        if(correctly) {
            final int NEXT_SESSION = session + 1;
            if(session < sessions.length - 1) sessions[NEXT_SESSION].keep(card);
        }
        else {
            sessions[session].keep(card);
        }
    }

    private void arrangeSessions() {
        Arrays.sort(sessions, (s1, s2) ->
            s1.deadline().isAfter(s2.deadline()) ? 1 : s1.deadline().isBefore(s2.deadline()) ? -1 : 0
        );
    }
}
