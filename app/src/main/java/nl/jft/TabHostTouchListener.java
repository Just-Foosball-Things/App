package nl.jft;

import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

/**
 * @author Oscar de Leeuw
 */

public class TabHostTouchListener implements OnTouchListener {

    private final int MIN_DISTANCE = 150;
    private final OverviewTabController tabController;
    private float x1, x2;

    public TabHostTouchListener(OverviewTabController tabController) {
        this.tabController = tabController;
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                x1 = event.getX();
                break;
            case MotionEvent.ACTION_UP:
                x2 = event.getX();
                float deltaX = x2 - x1;

                if (Math.abs(deltaX) > MIN_DISTANCE) {
                    // Left to Right swipe action
                    if (x2 > x1) {
                        tabController.switchTabs(SwipeDirection.LEFT);
                    }
                    // Right to left swipe action
                    else {
                        tabController.switchTabs(SwipeDirection.RIGHT);
                    }
                }
                break;
        }
        return true;
    }
}
