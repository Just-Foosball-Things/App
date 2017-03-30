package nl.jft;

import android.content.res.Resources;
import android.widget.TabHost;

/**
 * @author Oscar de Leeuw
 */

public class OverviewTabController {

    private final TabHost host;
    private final Resources res;

    public OverviewTabController(TabHost host, Resources res) {
        this.host = host;
        this.res = res;
    }

    public void switchTabs(SwipeDirection direction) {
        switch (direction) {
            case LEFT:
                if (host.getCurrentTab() != 0)
                    host.setCurrentTab(host.getCurrentTab() - 1);
                break;
            case RIGHT:
                if (host.getCurrentTab() != (host.getTabWidget()
                        .getTabCount() - 1))
                    host.setCurrentTab(host.getCurrentTab() + 1);
                break;
            case UP:
            case DOWN:
                break;
        }
    }

    public void setupTabHost() {
        host.setup();

        TabHost.TabSpec spec = createSpec(host, R.string.overview_tab_ranking, R.id.overview_tab_ranking, R.string.overview_tab_ranking);
        host.addTab(spec);

        spec = createSpec(host, R.string.overview_tab_matches, R.id.overview_tab_matches, R.string.overview_tab_matches);
        host.addTab(spec);

        spec = createSpec(host, R.string.overview_tab_profile, R.id.overview_tab_profile, R.string.overview_tab_profile);
        host.addTab(spec);

        host.setOnTouchListener(new TabHostTouchListener(this));
        host.setOnTabChangedListener(new AnimatedTabHostListener(host));
    }

    private TabHost.TabSpec createSpec(TabHost host, int tabSpecString, int content, int indicator) {
        TabHost.TabSpec spec = host.newTabSpec(res.getString(tabSpecString));
        spec.setContent(content);
        spec.setIndicator(res.getString(indicator));
        return spec;
    }
}
