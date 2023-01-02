package cz.mateusz.flashcardy.tdd.study.leitner;

public class StudyInterval {

    private final long seconds;

    private StudyInterval(long seconds) {
        this.seconds = seconds;
    }

    public static StudyInterval days(int n) {
        int daySeconds = 60 * 60 * 24;
        return new StudyInterval(n * (daySeconds));
    }

    public static StudyInterval weeks(int n) {
        int weekSeconds = (60 * 60 * 24) * 7;
        return new StudyInterval(n * weekSeconds);
    }

    public long inDays() {
        int daySeconds = 60 * 60 * 24;
        return seconds / daySeconds;
    }

    public long inWeeks() {
        int weekSeconds = (60 * 60 * 60) * 7;
        return seconds / weekSeconds;
    }

    public long inHours() {
        int hourSeconds = (60 * 60);
        return seconds / hourSeconds;
    }

    public long inSeconds() {
        return seconds;
    }
}
