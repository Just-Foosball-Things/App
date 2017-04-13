package nl.jft.util;

import android.content.Context;
import android.graphics.Typeface;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;

import nl.jft.R;
import nl.jft.logic.match.Match;
import nl.jft.logic.participant.Participant;

public final class LabelUtil {

    private static final String ICON_INCREASE_WINNER = "▲";
    private static final String ICON_INCREASE_LOSER = "▼";

    private static Context context;

    private LabelUtil() {
        throw new UnsupportedOperationException("Should not be instantiated.");
    }

    public static void initialize(Context context) {
        LabelUtil.context = context;
    }

    public static Spannable getGoalsSpannable(Match match, Participant participant) {
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

    public static Spannable getRatingSpannable(Match match, Participant participant) {
        boolean winner = MatchUtil.isWinner(match, participant);
        int rating = ParticipantUtil.getRoundedRating(participant);

        String ratingString = Integer.toString(rating);
        String content = String.format("%s", ratingString);

        int color = getColor(winner);
        int style = getStyleSpan(winner);

        SpannableStringBuilder builder = new SpannableStringBuilder(content);
        builder.setSpan(new ForegroundColorSpan(color), content.indexOf(ratingString), content.indexOf(ratingString) + ratingString.length(), Spannable.SPAN_INCLUSIVE_INCLUSIVE);
        builder.setSpan(new StyleSpan(style), content.indexOf(ratingString), content.indexOf(ratingString) + ratingString.length(), Spannable.SPAN_INCLUSIVE_INCLUSIVE);

        return builder;
    }

    public static Spannable getRatingDifferenceSpannable(Match match, Participant participant) {
        boolean winner = MatchUtil.isWinner(match, participant);

        String increase = getIncreaseIcon(winner) + (winner ? Double.toString(20.0) : Double.toString(10.0));
        String content = String.format("%s", increase);

        int color = getColor(winner);
        int style = getStyleSpan(winner);

        SpannableStringBuilder builder = new SpannableStringBuilder(content);
        builder.setSpan(new ForegroundColorSpan(color), content.indexOf(increase), content.indexOf(increase) + increase.length(), Spanned.SPAN_INCLUSIVE_INCLUSIVE);
        builder.setSpan(new StyleSpan(style), content.indexOf(increase), content.indexOf(increase) + increase.length(), Spanned.SPAN_INCLUSIVE_INCLUSIVE);

        return builder;
    }

    public static Spannable getRatingAndDifferenceSpannable(Match match, Participant participant) {
        boolean winner = MatchUtil.isWinner(match, participant);
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

    public static int getColor(boolean winner) {
        return winner ? getWinnerColor() : getLoserColor();
    }

    public static int getWinnerColor() {
        return context.getResources().getColor(R.color.overview_list_matches_item_winner);
    }

    public static int getLoserColor() {
        return context.getResources().getColor(R.color.overview_list_matches_item_loser);
    }

    public static int getStyleSpan(boolean winner) {
        return winner ? getWinnerStyleSpan() : getLoserStyleSpan();
    }

    public static int getWinnerStyleSpan() {
        return Typeface.BOLD;
    }

    public static int getLoserStyleSpan() {
        return Typeface.NORMAL;
    }

    public static String getIncreaseIcon(boolean winner) {
        return winner ? ICON_INCREASE_WINNER : ICON_INCREASE_LOSER;
    }

}
