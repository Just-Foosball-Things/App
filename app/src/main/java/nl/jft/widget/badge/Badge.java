package nl.jft.widget.badge;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import nl.jft.R;


/**
 * @author Oscar de Leeuw.
 */

public class Badge extends View {

    private final float TITLE_STRING_SIZE_FACTOR = 1f;
    private final float TITLE_STRING_BASE_STRING_SIZE = 40f;
    private final float TITLE_STRING_BASE_MIN_RECT = 30f;
    private final float TITLE_STRING_BASE_MAX_RECT = 300f;
    private final float TITLE_STRING_X_OFFSET = 200f;
    private final float TITLE_STRING_Y_OFFSET = 400f;
    private final float TITLE_STRING_BASE_START_ANGLE = 210f;
    private final float TITLE_STRING_BASE_SWEEP = 120f;
    private final float TITLE_STRING_BASE_STRING_WIDTH = 276f;
    //private final float ANGLE_COEFFICIENT = 15f/63f;

    private Bitmap profileImage;
    private String title;
    private String name;

    private Path upperArc;
    private Paint titlePaint;

    public Badge(Context context) {
        this(context, null);
    }

    public Badge(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        TypedArray a = context.getTheme().obtainStyledAttributes(
                attrs,
                R.styleable.Badge,
                0, 0);

        try {
            title = a.getString(R.styleable.Badge_title);
            profileImage = getBitmap(R.styleable.Badge_drawable);
            name = a.getString(R.styleable.Badge_name);
        } finally {
            a.recycle();
        }

        upperArc = new Path();
        titlePaint = getPaintForText(title);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        //float deltaTitleWidth = TITLE_STRING_BASE_STRING_WIDTH - titlePaint.measureText(title);

        upperArc.addArc(TITLE_STRING_BASE_MIN_RECT * TITLE_STRING_SIZE_FACTOR + TITLE_STRING_X_OFFSET,
                TITLE_STRING_BASE_MIN_RECT * TITLE_STRING_SIZE_FACTOR + TITLE_STRING_Y_OFFSET,
                TITLE_STRING_BASE_MAX_RECT * TITLE_STRING_SIZE_FACTOR + TITLE_STRING_X_OFFSET,
                TITLE_STRING_BASE_MAX_RECT * TITLE_STRING_SIZE_FACTOR + TITLE_STRING_Y_OFFSET,
                TITLE_STRING_BASE_START_ANGLE /*+ deltaTitleWidth * ANGLE_COEFFICIENT*/,
                TITLE_STRING_BASE_SWEEP);
        canvas.drawTextOnPath(title, upperArc, 0, 0, titlePaint);
    }

    public Bitmap getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(int resourceId) {
        profileImage = getBitmap(resourceId);
        invalidate();
    }

    private Bitmap getBitmap(int resourceId) {
        return BitmapFactory.decodeResource(getResources(), resourceId);
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
        titlePaint = getPaintForText(title);
        invalidate();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        invalidate();
    }

    private Paint getPaintForText(String text) {
        Paint ret = new Paint(Paint.ANTI_ALIAS_FLAG);
        ret.setStyle(Paint.Style.FILL_AND_STROKE);
        ret.setColor(getResources().getColor(R.color.black_overlay));
        ret.setTextSize(TITLE_STRING_BASE_STRING_SIZE * TITLE_STRING_SIZE_FACTOR);

        Paint test = new Paint(Paint.ANTI_ALIAS_FLAG);
        test.setStyle(Paint.Style.FILL_AND_STROKE);
        test.setColor(getResources().getColor(R.color.black_overlay));
        test.setTextSize(TITLE_STRING_BASE_STRING_SIZE * TITLE_STRING_SIZE_FACTOR + 1);

        float pixelsPerTextSize = test.measureText(text) - ret.measureText(text);
        float deltaTextSize = (TITLE_STRING_BASE_STRING_WIDTH - ret.measureText(text)) / pixelsPerTextSize;

        ret.setTextSize(TITLE_STRING_BASE_STRING_SIZE * TITLE_STRING_SIZE_FACTOR + deltaTextSize);
        return ret;
    }
}
