package cz.mateusz.flashcardy.tdd.study.leitner;

import java.time.LocalDateTime;

public class Flashcard {

    private String question;

    private String answer;

    private LocalDateTime attemptDate;

    private int successes;

    private int failures;

    public Flashcard(String question, String answer) {
        this.question = question;
        this.answer = answer;
    }

    public String getQuestion() {
        return question;
    }

    public String getAnswer() {
        return answer;
    }

    public void attempt(boolean correct) {
        attemptDate = LocalDateTime.now();
        if(correct) successes++;
        else failures++;
    }

    public int attempts() {
        return successes + failures;
    }

    public LocalDateTime getAttemptDate() {
        return this.attemptDate;
    }
}
