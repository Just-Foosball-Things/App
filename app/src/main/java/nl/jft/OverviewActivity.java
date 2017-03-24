package nl.jft;

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TabHost;

//TODO Fix gestureListener to be nicer.
//TODO Make a seperate class for constructing the tabhost.

public class OverviewActivity extends Activity {

    static final int MIN_DISTANCE = 150;
    private float x1, x2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_overview);
        setupTabHost();
    }

    private void setupTabHost() {
        final TabHost host = (TabHost) findViewById(R.id.overview_tabhost);
        host.setup();

        host.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        System.out.println("DOWN");
                        x1 = event.getX();
                        break;
                    case MotionEvent.ACTION_UP:
                        x2 = event.getX();
                        float deltaX = x2 - x1;

                        if (Math.abs(deltaX) > MIN_DISTANCE) {
                            // Left to Right swipe action
                            if (x2 > x1) {
                                switchTabs(host, true);
                                //Toast.makeText(OverviewActivity.this, "Left to Right swipe [Next]", Toast.LENGTH_SHORT).show ();
                            }
                            // Right to left swipe action
                            else {
                                switchTabs(host, false);
                                //Toast.makeText(OverviewActivity.this, "Right to Left swipe [Previous]", Toast.LENGTH_SHORT).show ();
                            }
                        } else {
                            //Toast.makeText(OverviewActivity.this, "TAP TAP", Toast.LENGTH_SHORT).show();
                        }
                        break;
                }
                return true;
            }
        });

        TabHost.TabSpec spec = createSpec(host, R.string.overview_tab_ranking, R.id.overview_tab_ranking, R.string.overview_tab_ranking);
        host.addTab(spec);

        spec = createSpec(host, R.string.overview_tab_matches, R.id.overview_tab_matches, R.string.overview_tab_matches);
        host.addTab(spec);

        spec = createSpec(host, R.string.overview_tab_profile, R.id.overview_tab_profile, R.string.overview_tab_profile);
        host.addTab(spec);
    }

    private TabHost.TabSpec createSpec(TabHost host, int tabSpecString, int content, int indicator) {
        TabHost.TabSpec spec = host.newTabSpec(getResources().getString(tabSpecString));
        spec.setContent(content);
        spec.setIndicator(getResources().getString(indicator));
        return spec;
    }

    public void switchTabs(TabHost host, boolean direction) {
        if (direction) // true = move left
        {
            if (host.getCurrentTab() == 0)
                host.setCurrentTab(host.getTabWidget().getTabCount() - 1);
            else
                host.setCurrentTab(host.getCurrentTab() - 1);
        } else
        // move right
        {
            if (host.getCurrentTab() != (host.getTabWidget()
                    .getTabCount() - 1))
                host.setCurrentTab(host.getCurrentTab() + 1);
            else
                host.setCurrentTab(0);
        }
    }
}
