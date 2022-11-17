package cz.mateusz.flashcardy.tdd.model.flashcard;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Flashcard {

    private String notion;

    private String definition;

    private List<String> notionUsageExamples = new ArrayList<>();

    public Flashcard(String notion, String definition) {
        setNotion(notion);
        setDefinition(definition);
    }

    public void setNotion(String notion) {
        if(notion == null) {
            throw new IllegalArgumentException("Notion must be provided!");
        }
        if(notion.isBlank()) {
            throw  new IllegalArgumentException("Notion must not be blank!");
        }
        this.notion = notion;
    }

    public void setDefinition(String definition) {
        if(definition == null) {
            throw new IllegalArgumentException("Definition must be provided!");
        }
        if(definition.isBlank()) {
            throw new IllegalArgumentException("Definition must not be blank!");
        }
        this.definition = definition;
    }

    public void addManyNotionUsageExamples(String...examples) {
        for(String example : examples) {
            addNotionUsageExample(example);
        }
    }

    public void addNotionUsageExample(String example) {
        if(example == null || example.isBlank()) return;
        notionUsageExamples.add(example);
    }

    public List<String> getNotionUsageExamples() {
        return Collections.unmodifiableList(this.notionUsageExamples);
    }
}
