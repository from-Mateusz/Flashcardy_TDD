package cz.mateusz.flashcardy.tdd.practice;

import cz.mateusz.flashcardy.tdd.flashcard.Flashcard;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class LeitnerSystemTests {

    public void when_decide_on_repetition_system_then_prepare_to_use_and_provide_user_with_ready_to_interact_system() {
        final LeitnerSystem sessionBasedSystem = RepetitionSystems.LeitnerSystems.sessions();
        final LeitnerSystem proficiencyBasedSystem = RepetitionSystems.LeitnerSystems.proficiency();
    }

    public static class RepetitionSystems {
        public static class LeitnerSystems {
            static LeitnerSystem<SessionBox> sessions() {
                return SessionRepetitionsSystem.system();
            }
            static LeitnerSystem<ProficiencyBox> proficiency() {
                return ProficiencyRepetitionsSystem.system();
            }
        }
    }

    private static class LeitnerSystem<B extends Box<B>> {

        private List<B> boxes;

        private LeitnerSystem(List<B> boxes) {
            this.boxes = boxes;
        }
    }

    private final static class SessionRepetitionsSystem extends LeitnerSystem<SessionBox> {

        private SessionBox current;

        private SessionBox retired;

        private SessionRepetitionsSystem(List<SessionBox> boxes) {
            super(boxes);
            this.current = new SessionBox(IntStream.range(0, boxes.size()).toArray());
            this.retired = new SessionBox();
        }

        public static SessionRepetitionsSystem system() {
           return new SessionRepetitionsSystem(prepareSessions());
        }

        private static List<SessionBox> prepareSessions() {
            final List<SessionBox> sessions = new ArrayList<>();
            final int LAST_SESSION_ID = 9;
            sessions.add(new SessionBox(new int[] {0, 2, 5, LAST_SESSION_ID}));
            final int REMAINING_SESSIONS = 10;
            final int SESSIONS_PER_BOX = 4;
            for(int i = 1; i < REMAINING_SESSIONS; i++) {
                final SessionBox previous = sessions.get(i - 1);
                int[] session_ids = new int[SESSIONS_PER_BOX];
                for(int s = 0; s < SESSIONS_PER_BOX; s++) {
                    if(previous.get(s) == LAST_SESSION_ID) session_ids[s] = LAST_SESSION_ID - LAST_SESSION_ID;
                    else session_ids[s] = previous.get(s) + 1;
                }
            }
            return sessions;
        }
    }

    public final static class ProficiencyRepetitionsSystem extends LeitnerSystem<ProficiencyBox> {
        private ProficiencyRepetitionsSystem(List<ProficiencyBox> boxes) {
            super(boxes);
        }

        public static ProficiencyRepetitionsSystem system() {
            return new ProficiencyRepetitionsSystem(new ArrayList<>());
        }
    }

    private interface RevisionCycle<T> {
        T revise(RevisionGap gap);
    }

    private static class RevisionGap {

        private long seconds;

        private RevisionGap(long seconds) {
            this.seconds = seconds;
        }

        public static RevisionGap minutes(int minutes) {
            final long gap = minutes * 60;
            return new RevisionGap(gap);
        }

        public static RevisionGap hours(int hours) {
            final long gap = hours * (long) Math.pow(60, 60);
            return new RevisionGap(gap);
        }

        public static RevisionGap days(int days) {
            final long gap = days * 24 * (long) Math.pow(60, 60);
            return new RevisionGap(gap);
        }

        public long getSeconds() {
            return seconds;
        }
    }

    private static abstract class Box<T extends Box<T>> implements RevisionCycle<T> {

        private List<Flashcard> flashcards = new ArrayList<>();

        private LocalDateTime revisionDate;

        public Box store(Flashcard... flashcards) {
            for(Flashcard flashcard : flashcards) this.flashcards.add(flashcard);
            return this;
        }

        @Override
        public T revise(RevisionGap gap) {
            final LocalDateTime currentTime = LocalDateTime.now();
            this.revisionDate = currentTime.plusSeconds(gap.getSeconds());
            return (T) this;
        }
    }

    private static class ProficiencyBox extends Box<ProficiencyBox> {

        private final int level;

        public ProficiencyBox(int level) {
            this.level = level;
        }

        public int getLevel() {
            return level;
        }
    }

    private static class SessionBox extends Box<SessionBox> {

        private final int[] sessions;

        public SessionBox(int... sessions) {
            this.sessions = sessions;
        }

        public int first() {
            return sessions[0];
        }

        public int last() {
            return sessions[sessions.length - 1];
        }

        public int get(int order) {
            if(order > sessions.length) throw new IllegalArgumentException(String.format("There are only %d sessions", sessions.length));
            return sessions[order];
        }

        public int after(int session) {
            if(!containsSession(session)) throw new IllegalArgumentException(String.format("There is no session with id: %d", session));
            if(isLastSession(session)) return first();

            int next = first();
            for(int s = 1; s < sessions.length; s++) {
                if(sessions[s] == session) {
                    next = sessions[s + 1];
                    break;
                }
            }

            return next;
        }

        public boolean isLastSession(int session) {
            final int last = sessions.length - 1;
            return session == sessions[last];
        }

        public boolean containsSession(int session) {
            for(int sess : sessions) {
                if(sess == session) return true;
            }
            return false;
        }
    }
}
