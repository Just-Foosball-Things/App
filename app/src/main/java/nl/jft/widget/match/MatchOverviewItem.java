package nl.jft.widget.match;

import nl.jft.logic.match.Match;

public final class MatchOverviewItem {

    private final Match match;

    public MatchOverviewItem(Match match) {
        this.match = match;
    }

    public Match getMatch() {
        return match;
    }

}
