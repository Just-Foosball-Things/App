package nl.jft.logic.util.builder;

import nl.jft.logic.LogicConstants;
import nl.jft.logic.match.Rule;

/**
 * @author Lesley
 */
public final class RuleBuilder {

    private int id;
    private String description;

    public RuleBuilder() {
        id = LogicConstants.INTERNAL_ID;
        description = "description";
    }

    public RuleBuilder withId(int id) {
        this.id = id;
        return this;
    }

    public RuleBuilder withDescription(String description) {
        this.description = description;
        return this;
    }

    public Rule build() {
        return new Rule(id, description);
    }

    public Rule build2() {
        return new Rule(description);
    }

}
