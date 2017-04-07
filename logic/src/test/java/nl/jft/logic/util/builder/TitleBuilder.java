package nl.jft.logic.util.builder;

import nl.jft.logic.LogicConstants;
import nl.jft.logic.participant.Title;

/**
 * @author Lesley
 */
public final class TitleBuilder {

    private int id;
    private String name;

    public TitleBuilder() {
        id = LogicConstants.INTERNAL_ID;
        name = "title";
    }

    public TitleBuilder withId(int id) {
        this.id = id;
        return this;
    }

    public TitleBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public Title build() {
        return new Title(id, name);
    }

    public Title build2() {
        return new Title(name);
    }

}
