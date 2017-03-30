package nl.jft.widget.match;

import android.graphics.Typeface;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import nl.jft.R;
import nl.jft.logic.participant.Participant;
import nl.jft.widget.AnimatedExpandableListView;
import nl.jft.widget.util.MatchUtil;

public final class MatchOverviewHeaderDrawer implements Drawer {

    private final View view;
    private final MatchOverviewHeader header;

    public MatchOverviewHeaderDrawer(View view, MatchOverviewHeader header) {
        this.view = view;
        this.header = header;
    }

    @Override
    public void draw(int groupPosition, int childPosition, boolean expanded) {
        drawBothParticipantNames();
        drawArrowIndicator(groupPosition, expanded);
    }

    private void drawBothParticipantNames() {
        drawFirstParticipantName();
        drawSecondParticipantName();
    }

    private void drawFirstParticipantName() {
        drawParticipantName(R.id.overview_list_matches_item_first_participant, header.getMatch().getFirstParticipant());
    }

    private void drawSecondParticipantName() {
        drawParticipantName(R.id.overview_list_matches_item_second_participant, header.getMatch().getSecondParticipant());
    }

    private void drawParticipantName(int resource, Participant participant) {
        TextView text = (TextView) view.findViewById(resource);
        if (text == null) {
            return;
        }

        text.setText(getNameSpannable(participant));
    }

    private Spannable getNameSpannable(Participant participant) {
        boolean winner = MatchUtil.isWinner(header.getMatch(), participant);

        String name = participant.getName();
        String content = String.format("%s", name);

        int color = getColor(winner);
        int style = getStyleSpan(winner);

        SpannableStringBuilder builder = new SpannableStringBuilder(content);
        builder.setSpan(new ForegroundColorSpan(color), content.indexOf(name), content.indexOf(name) + name.length(), Spannable.SPAN_INCLUSIVE_INCLUSIVE);
        builder.setSpan(new StyleSpan(style), content.indexOf(name), content.indexOf(name) + name.length(), Spannable.SPAN_INCLUSIVE_INCLUSIVE);

        return builder;
    }

    private void drawArrowIndicator(final int position, final boolean expanded) {
        ImageView indicatorExpand = (ImageView) view.findViewById(R.id.overview_list_matches_icon_expand);
        ImageView indicatorContract = (ImageView) view.findViewById(R.id.overview_list_matches_icon_contract);

        if (indicatorExpand == null || indicatorContract == null) {
            return;
        }

        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AnimatedExpandableListView list = (AnimatedExpandableListView) view.getParent();

                if (expanded) {
                    list.collapseGroupWithAnimation(position);
                } else {
                    list.expandGroupWithAnimation(position);
                }
            }
        };

        indicatorExpand.setOnClickListener(listener);
        indicatorContract.setOnClickListener(listener);

        indicatorExpand.setVisibility(expanded ? View.INVISIBLE : View.VISIBLE);
        indicatorContract.setVisibility(expanded ? View.VISIBLE : View.INVISIBLE);
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
