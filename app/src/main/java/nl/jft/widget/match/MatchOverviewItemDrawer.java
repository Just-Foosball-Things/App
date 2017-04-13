package nl.jft.widget.match;

import android.view.View;
import android.widget.TextView;

import nl.jft.R;
import nl.jft.logic.participant.Participant;
import nl.jft.util.LabelUtil;

public final class MatchOverviewItemDrawer implements Drawer {

    private static final String ICON_INCREASE_WINNER = "▲";
    private static final String ICON_INCREASE_LOSER = "▼";

    private final View view;
    private final MatchOverviewItem item;

    public MatchOverviewItemDrawer(View view, MatchOverviewItem item) {
        this.view = view;
        this.item = item;
    }

    private static String getIncreaseIcon(boolean winner) {
        return winner ? ICON_INCREASE_WINNER : ICON_INCREASE_LOSER;
    }

    @Override
    public void draw(int groupPosition, int childPosition, boolean expanded) {
        drawBothParticipantRatings();
        drawBothParticipantGoals();
    }

    private void drawBothParticipantRatings() {
        drawFirstParticipantRating();
        drawSecondParticipantRating();
    }

    private void drawFirstParticipantRating() {
        drawParticipantRating(R.id.overview_list_matches_item_first_rating, item.getMatch().getFirstParticipant());
    }

    private void drawSecondParticipantRating() {
        drawParticipantRating(R.id.overview_list_matches_item_second_rating, item.getMatch().getSecondParticipant());
    }

    private void drawParticipantRating(int resource, Participant participant) {
        TextView text = (TextView) view.findViewById(resource);
        text.setText(LabelUtil.getRatingAndDifferenceSpannable(item.getMatch(), participant));
    }

    private void drawBothParticipantGoals() {
        drawFirstParticipantGoals();
        drawSecondParticipantGoals();
    }

    private void drawFirstParticipantGoals() {
        drawParticipantGoals(R.id.overview_list_matches_item_first_goals, item.getMatch().getFirstParticipant());
    }

    private void drawSecondParticipantGoals() {
        drawParticipantGoals(R.id.overview_list_matches_item_second_goals, item.getMatch().getSecondParticipant());
    }

    private void drawParticipantGoals(int resource, Participant participant) {
        TextView text = (TextView) view.findViewById(resource);
        text.setText(LabelUtil.getGoalsSpannable(item.getMatch(), participant));
    }

}
