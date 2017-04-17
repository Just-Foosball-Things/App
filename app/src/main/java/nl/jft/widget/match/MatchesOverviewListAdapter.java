package nl.jft.widget.match;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nl.jft.R;
import nl.jft.logic.match.Match;
import nl.jft.widget.AnimatedExpandableListView;

public final class MatchesOverviewListAdapter extends AnimatedExpandableListView.AnimatedExpandableListAdapter {

    private final Context context;
    private final List<MatchOverviewHeader> headers = new ArrayList<>();
    private final Map<MatchOverviewHeader, MatchOverviewItem> items = new HashMap<>();

    public MatchesOverviewListAdapter(Context context) {
        this.context = context;
    }

    public void addMatch(Match match) {
        MatchOverviewHeader header = new MatchOverviewHeader(match);
        MatchOverviewItem item = new MatchOverviewItem(match);

        headers.add(header);
        items.put(header, item);

        notifyDataSetChanged();
    }

    public void addMatches(List<Match> matches) {
        items.clear();
        headers.clear();
        notifyDataSetChanged();

        for (Match match : matches) {
            addMatch(match);
        }
    }

    @Override
    public View getRealChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        convertView = inflateItemView(convertView);

        MatchOverviewItem item = getChild(groupPosition, childPosition);
        Drawer itemDrawer = new MatchOverviewItemDrawer(convertView, item);
        itemDrawer.draw(groupPosition, childPosition, false);

        return convertView;
    }

    private View inflateItemView(View view) {
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.overview_list_matches_item, null);
        }

        return view;
    }

    @Override
    public int getRealChildrenCount(int groupPosition) {
        return 1;
    }

    @Override
    public int getGroupCount() {
        return items.size();
    }

    @Override
    public MatchOverviewHeader getGroup(int groupPosition) {
        return headers.get(groupPosition);
    }

    @Override
    public MatchOverviewItem getChild(int groupPosition, int childPosition) {
        MatchOverviewHeader header = getGroup(groupPosition);
        return items.get(header);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        convertView = inflateHeaderView(convertView);

        MatchOverviewHeader header = getGroup(groupPosition);
        Drawer headerDrawer = new MatchOverviewHeaderDrawer(convertView, header);
        headerDrawer.draw(groupPosition, -1, isExpanded);

        return convertView;
    }

    private View inflateHeaderView(View view) {
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.overview_list_matches_header, null);
        }

        return view;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

}
