package nl.jft.widget.badge;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import nl.jft.R;


/**
 * @author Oscar de Leeuw.
 */

public class Badge extends View {

    private final static float NAME_STRING_TEXT_SIZE = 60f;
    private final float TITLE_STRING_SIZE_FACTOR = 1f;
    private final float TITLE_STRING_BASE_STRING_SIZE = 40f;
    private final float TITLE_STRING_BASE_MIN_RECT = 30f;
    private final float TITLE_STRING_BASE_MAX_RECT = 300f;
    private final float TITLE_STRING_X_OFFSET = -20f;
    private final float TITLE_STRING_Y_OFFSET = 0f;
    private final float TITLE_STRING_BASE_START_ANGLE = 210f;
    private final float TITLE_STRING_BASE_SWEEP = 120f;
    //private final float ANGLE_COEFFICIENT = 15f/63f;
    private final float TITLE_STRING_BASE_STRING_WIDTH = 276f;
    private final int PROFILE_IMAGE_BASE_X = 40;
    private final int PROFILE_IMAGE_BASE_Y = 40;
    private final int PROFILE_IMAGE_BASE_WIDTH = 220;
    private final int PROFILE_IMAGE_BASE_HEIGHT = 220;

    private Drawable profileImage;
    private Drawable profileImageClipping;
    private String title;
    private String name;

    private Path upperArc;
    private Paint titlePaint;
    private Paint namePaint;

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
            profileImage = a.getDrawable(R.styleable.Badge_drawable);
            name = a.getString(R.styleable.Badge_name);
        } finally {
            a.recycle();
        }

        profileImageClipping = getResources().getDrawable(R.drawable.profile_image_clipping);
        upperArc = new Path();
        titlePaint = getPaintForTitle(title);
        namePaint = getPaintForName(name);
    }

    public Drawable getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(int resourceId) {
        profileImage = getResources().getDrawable(resourceId);
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
        titlePaint = getPaintForTitle(title);
        invalidate();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        namePaint = getPaintForName(name);
        invalidate();
    }

    private Paint getPaintForName(String text) {
        Paint ret = new Paint(Paint.ANTI_ALIAS_FLAG);
        ret.setStyle(Paint.Style.FILL_AND_STROKE);
        ret.setColor(getResources().getColor(R.color.black_overlay));
        ret.setTextSize(NAME_STRING_TEXT_SIZE);

        return ret;
    }

    private Paint getPaintForTitle(String text) {
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
        centerText(canvas);

        /*profileImageClipping.setBounds(30, 40, 300, 300);
        profileImageClipping.draw(canvas);*/
        profileImage.setBounds(PROFILE_IMAGE_BASE_X,
                PROFILE_IMAGE_BASE_Y,
                PROFILE_IMAGE_BASE_WIDTH + PROFILE_IMAGE_BASE_X,
                PROFILE_IMAGE_BASE_HEIGHT + PROFILE_IMAGE_BASE_Y);
        profileImage.draw(canvas);
    }

    private void centerText(Canvas canvas) {
        float x;
        float y;

        float width = namePaint.measureText(name);
        x = (PROFILE_IMAGE_BASE_WIDTH - width) / 2 + PROFILE_IMAGE_BASE_X;
        y = PROFILE_IMAGE_BASE_HEIGHT + PROFILE_IMAGE_BASE_Y + namePaint.getTextSize() - 15;

        canvas.drawText(name, x, y, namePaint);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension((int) (TITLE_STRING_BASE_MAX_RECT + TITLE_STRING_Y_OFFSET), (int) (PROFILE_IMAGE_BASE_HEIGHT + PROFILE_IMAGE_BASE_Y + namePaint.getTextSize()));
    }
}
