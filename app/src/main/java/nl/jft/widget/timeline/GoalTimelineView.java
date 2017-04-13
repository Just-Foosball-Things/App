package nl.jft.widget.timeline;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import nl.jft.R;
import nl.jft.logic.match.Goal;
import nl.jft.logic.match.Match;
import nl.jft.logic.participant.Participant;
import nl.jft.util.MatchUtil;

public class GoalTimelineView extends View {

    private static final int FOOTBALL_LINE_HEIGHT = 100;
    private static final int MARGIN_FOOTBALL = 15;

    private static final int BASELINE_THICKNESS = 10;
    private static final int WINNER_LOSER_LINES_THINKNESS = 15;

    private final Paint paintBaseline;
    private final Paint paintWinnerLines;
    private final Paint paintLoserLines;

    private final Bitmap bitmapFootball;
    private final List<Rect> bitmapLocations = new ArrayList<>();

    private Match match;
    private GoalClickedListener listener;

    public GoalTimelineView(Context context) {
        this(context, null);
    }

    public GoalTimelineView(Context context, AttributeSet set) {
        super(context, set);

        bitmapFootball = BitmapFactory.decodeResource(getResources(), R.drawable.football);

        paintBaseline = new Paint();
        paintBaseline.setColor(ContextCompat.getColor(context, R.color.goal_timeline_baseline));
        paintBaseline.setStrokeWidth(BASELINE_THICKNESS);

        paintWinnerLines = new Paint();
        paintWinnerLines.setColor(ContextCompat.getColor(context, R.color.overview_list_matches_item_winner));
        paintWinnerLines.setStrokeWidth(WINNER_LOSER_LINES_THINKNESS);

        paintLoserLines = new Paint();
        paintLoserLines.setColor(ContextCompat.getColor(context, R.color.overview_list_matches_item_loser));
        paintLoserLines.setStrokeWidth(WINNER_LOSER_LINES_THINKNESS);
    }

    public void setOnGoalClickedListener(GoalClickedListener listener) {
        this.listener = listener;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_UP) {
            Goal goal = getGoalFromPoint((int) event.getX(), (int) event.getY());
            if (goal != null && listener != null) {
                listener.onGoalClicked(match, goal);
            }

            return performClick();
        }

        return true;
    }

    private Goal getGoalFromPoint(int x, int y) {
        int index = -1;
        for (int i = 0; i < bitmapLocations.size(); i++) {
            Rect bounds = bitmapLocations.get(i);
            if (bounds.contains(x, y)) {
                index = i;
                break;
            }
        }

        if (index < 0) {
            return null;
        }

        return match.getGoals().get(index);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        //setMeasuredDimension((int) (TITLE_STRING_BASE_MAX_RECT + TITLE_STRING_Y_OFFSET), (int) (PROFILE_IMAGE_BASE_HEIGHT + PROFILE_IMAGE_BASE_Y + namePaint.getTextSize()));
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int width = View.MeasureSpec.getSize(widthMeasureSpec);

        List<Goal> goals = match == null ? new ArrayList<Goal>() : match.getGoals();
        int total = goals.size() == 0 ? 19 : goals.size();

        int totalMarginWidth = MARGIN_FOOTBALL * total;
        int footballHeight = (width - totalMarginWidth) / total;

        int height = ((FOOTBALL_LINE_HEIGHT + footballHeight) * 2) + BASELINE_THICKNESS;
        setMeasuredDimension(width, height);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (match == null) {
            super.onDraw(canvas);
            return;
        }

        bitmapLocations.clear();

        drawBaseline(canvas);
        drawGoals(canvas);
    }

    private void drawBaseline(Canvas canvas) {
        int width = canvas.getWidth();
        int height = canvas.getHeight();

        int centerY = height / 2;
        canvas.drawLine(0, centerY, width, centerY, paintBaseline);
    }

    private void drawGoals(Canvas canvas) {
        List<Goal> goals = match.getGoals();
        int total = goals.size();

        for (int i = 0; i < total; i++) {
            drawGoal(canvas, goals.get(i), total, i);
        }
    }

    private void drawGoal(Canvas canvas, Goal goal, int total, int index) {
        boolean first = isFirstParticipant(goal.getParticipant());
        drawGoal(canvas, goal, total, index, first);
    }

    private void drawGoal(Canvas canvas, Goal goal, int total, int index, boolean top) {
        int width = canvas.getWidth();
        int height = canvas.getHeight();

        int centerY = height / 2;
        int totalMarginWidth = MARGIN_FOOTBALL * total;

        int footballWidth = (width - totalMarginWidth) / total;
        int footballHeight = footballWidth;

        int footballX = index == 0 ? MARGIN_FOOTBALL : index * (footballWidth + MARGIN_FOOTBALL);
        int footballY = centerY + (top ? -FOOTBALL_LINE_HEIGHT - footballHeight : FOOTBALL_LINE_HEIGHT);
        drawBall(canvas, footballX, footballY, footballWidth, footballHeight);

        Paint paint = MatchUtil.isWinner(match, goal.getParticipant()) ? paintWinnerLines : paintLoserLines;
        int lineX = footballX + (footballWidth / 2);
        int lineY = footballY + (top ? footballHeight : 0);
        drawLineToBall(canvas, lineX, lineY, lineX, centerY, paint);
    }

    private void drawBall(Canvas canvas, int x, int y, int width, int height) {
        Bitmap bitmapFootballScaled = Bitmap.createScaledBitmap(bitmapFootball, width, height, true);
        canvas.drawBitmap(bitmapFootballScaled, x, y, null);

        bitmapLocations.add(new Rect(x, y, x + width, y + height));
    }

    private void drawLineToBall(Canvas canvas, int startX, int startY, int endX, int endY, Paint paint) {
        canvas.drawLine(startX, startY, endX, endY, paint);
    }

    public void setMatch(Match match) {
        this.match = match;
    }

    private boolean isFirstParticipant(Participant participant) {
        return Objects.equals(match.getFirstParticipant(), participant);
    }

}
