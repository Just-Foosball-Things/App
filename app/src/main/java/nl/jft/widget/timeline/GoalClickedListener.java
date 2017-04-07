package nl.jft.widget.timeline;

import nl.jft.logic.match.Goal;
import nl.jft.logic.match.Match;

public interface GoalClickedListener {

    void onGoalClicked(Match match, Goal goal);

}
