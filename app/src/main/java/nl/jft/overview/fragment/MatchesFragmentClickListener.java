package nl.jft.overview.fragment;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ExpandableListView;

import java.util.List;

import nl.jft.logic.match.Match;
import nl.jft.match.MatchActivity;
import nl.jft.match.fragment.MatchOverviewFragment;

/**
 * @author Oscar de Leeuw.
 */

public class MatchesFragmentClickListener implements ExpandableListView.OnGroupClickListener, ExpandableListView.OnChildClickListener {

    private final List<Match> matches;

    MatchesFragmentClickListener(List<Match> matches) {
        this.matches = matches;
    }

    @Override
    public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
        startActivity(v.getContext(), groupPosition);
        return false;
    }

    @Override
    public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
        startActivity(v.getContext(), groupPosition);
        return true;
    }

    private void startActivity(Context context, int position) {
        Intent intent = new Intent(context, MatchActivity.class);
        intent.putExtra(MatchOverviewFragment.EXTRA_MATCH_ARGUMENT, matches.get(position));

        context.startActivity(intent);
    }
}
