package nl.jft.logic.util.builder;

import nl.jft.logic.LogicConstants;
import nl.jft.logic.match.Match;
import nl.jft.logic.match.MatchResult;

/**
 * @author Lesley
 */
public final class MatchResultBuilder {

    private int id;
    private Match match;

    public MatchResultBuilder() {
        id = LogicConstants.INTERNAL_ID;
        match = ObjectBuilder.match().build();
    }

    public MatchResultBuilder withId(int id) {
        this.id = id;
        return this;
    }

    public MatchResultBuilder withMatch(Match match) {
        this.match = match;
        return this;
    }

    public MatchResult build() {
        return new MatchResult(id, match);
    }

    public MatchResult build2() {
        return new MatchResult(match);
    }

}
