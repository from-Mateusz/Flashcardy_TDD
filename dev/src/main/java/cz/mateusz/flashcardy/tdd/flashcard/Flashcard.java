package cz.mateusz.flashcardy.tdd.flashcard;

import java.util.Objects;

public class Flashcard {

    private String notion;

    private String definition;

    public Flashcard(String notion, String definition) {
        setNotion(notion);
        setDefinition(definition);
    }

    public String getNotion() {
        return notion;
    }

    public void setNotion(String notion) {
        if(notion == null) {
            throw new IllegalArgumentException("Flashcard needs notion");
        }
        if(notion.isBlank()) {
            throw new IllegalArgumentException("Flashcard cannot have blank notion");
        }
        this.notion = notion;
    }

    public String getDefinition() {
        return definition;
    }

    public void setDefinition(String definition) {
        if(definition == null) {
            throw new IllegalArgumentException("Flashcard needs definition");
        }
        if(definition.isBlank()) {
            throw new IllegalArgumentException("Flashcard cannot have blank definition");
        }
        this.definition = definition;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Flashcard flashcard = (Flashcard) o;
        return Objects.equals(notion, flashcard.notion) && Objects.equals(definition, flashcard.definition);
    }

    @Override
    public int hashCode() {
        return Objects.hash(notion, definition);
    }

    @Override
    public String toString() {
        return "Flashcard{" +
                "notion='" + notion + '\'' +
                ", definition='" + definition + '\'' +
                '}';
    }
}
