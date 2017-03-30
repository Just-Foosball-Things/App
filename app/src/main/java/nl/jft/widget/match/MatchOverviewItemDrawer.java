package nl.jft.widget.match;

import android.graphics.Typeface;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.view.View;
import android.widget.TextView;

import nl.jft.R;
import nl.jft.logic.match.Match;
import nl.jft.logic.participant.Participant;
import nl.jft.util.MatchUtil;
import nl.jft.util.ParticipantUtil;

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
        text.setText(getRatingSpannable(participant));
    }

    private Spannable getRatingSpannable(Participant participant) {
        boolean winner = MatchUtil.isWinner(item.getMatch(), participant);
        int rating = ParticipantUtil.getRoundedRating(participant);

        String ratingString = Integer.toString(rating);
        String increase = getIncreaseIcon(winner) + (winner ? Double.toString(20.0) : Double.toString(10.0));
        String content = String.format("%s (%s)", ratingString, increase);

        int color = getColor(winner);
        int style = getStyleSpan(winner);

        SpannableStringBuilder builder = new SpannableStringBuilder(content);
        builder.setSpan(new ForegroundColorSpan(color), content.indexOf(ratingString), content.indexOf(ratingString) + ratingString.length(), Spannable.SPAN_INCLUSIVE_INCLUSIVE);
        builder.setSpan(new StyleSpan(style), content.indexOf(ratingString), content.indexOf(ratingString) + ratingString.length(), Spannable.SPAN_INCLUSIVE_INCLUSIVE);

        builder.setSpan(new ForegroundColorSpan(color), content.indexOf(increase), content.indexOf(increase) + increase.length(), Spanned.SPAN_INCLUSIVE_INCLUSIVE);
        builder.setSpan(new StyleSpan(style), content.indexOf(increase), content.indexOf(increase) + increase.length(), Spanned.SPAN_INCLUSIVE_INCLUSIVE);

        return builder;
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
        text.setText(getGoalsSpannable(participant));
    }

    private Spannable getGoalsSpannable(Participant participant) {
        Match match = item.getMatch();
        int goals = MatchUtil.getAmountOfGoals(match, participant);
        boolean winner = MatchUtil.isWinner(match, participant);

        String goalsString = Integer.toString(goals);
        String content = String.format("%s", goalsString);

        int color = getColor(winner);
        int style = getStyleSpan(winner);

        SpannableStringBuilder builder = new SpannableStringBuilder(content);
        builder.setSpan(new ForegroundColorSpan(color), content.indexOf(goalsString), content.indexOf(goalsString) + goalsString.length(), Spannable.SPAN_INCLUSIVE_INCLUSIVE);
        builder.setSpan(new StyleSpan(style), content.indexOf(goalsString), content.indexOf(goalsString) + goalsString.length(), Spannable.SPAN_INCLUSIVE_INCLUSIVE);

        return builder;
    }

    private int getColor(boolean winner) {
        return winner ? getWinnerColor() : getLoserColor();
    }

    private int getWinnerColor() {
        return view.getResources().getColor(R.color.overview_list_matches_item_winner);
    }

    private int getLoserColor() {
        return view.getResources().getColor(R.color.overview_list_matches_item_loser);
    }

    private int getStyleSpan(boolean winner) {
        return winner ? getWinnerStyleSpan() : getLoserStyleSpan();
    }

    private int getWinnerStyleSpan() {
        return Typeface.BOLD;
    }

    private int getLoserStyleSpan() {
        return Typeface.NORMAL;
    }

}
