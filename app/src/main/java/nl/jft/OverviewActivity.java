package nl.jft;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TabHost;

/**
 * @author Oscar de Leeuw
 */
public class OverviewActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_overview);

        TabHost host = (TabHost) findViewById(R.id.overview_tabhost);
        OverviewTabController tabController = new OverviewTabController(host, getResources());
        tabController.setupTabHost();
    }




}
