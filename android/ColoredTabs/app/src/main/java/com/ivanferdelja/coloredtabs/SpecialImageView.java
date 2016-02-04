package com.ivanferdelja.coloredtabs;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.widget.ImageView;


public class SpecialImageView extends ImageView {

    public enum Mode {
        CIRCLE,
        ROUNDED_CORNERS
    }

    private Path path;
    private Paint dstInPaint;
    Mode mode = Mode.CIRCLE;

    public SpecialImageView(Context context) {
        super(context);
        init(null);
    }

    public SpecialImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public SpecialImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    public SpecialImageView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(attrs);
    }

    private void init(AttributeSet attributeSet) {
        if (attributeSet != null) {
            TypedArray ta = getContext().obtainStyledAttributes(attributeSet, R.styleable.SpecialImageView);
            int value = ta.getInteger(R.styleable.SpecialImageView_mode, -1);
            if (value == 2) {
                mode = Mode.ROUNDED_CORNERS;
            }
        }

        path = new Path();
        dstInPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        dstInPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        int count = canvas.saveLayer(0, 0, getWidth(), getHeight(), null, 0);
        super.onDraw(canvas);
        // Note: Cannot directly clip the path on the canvas because the resulting region will have rough edges.
        // To resolve this we would need to turn on anti-aliasing which is not available for canvas clip regions.
        // Clip path is not anti-aliased and is not hw accelerated.
        if (Mode.CIRCLE == mode) {
            float halfWidth = canvas.getWidth() / 2;
            float halfHeight = canvas.getHeight() / 2;
            float radius = Math.max(halfWidth, halfHeight);
            path.addCircle(halfWidth, halfHeight, radius, Path.Direction.CCW);
            canvas.drawPath(path, dstInPaint);
        } else if (Mode.ROUNDED_CORNERS == mode) {
            path.addRoundRect(new RectF(canvas.getClipBounds()), 8, 8, Path.Direction.CCW);
            canvas.drawPath(path, dstInPaint);
        }
        canvas.restoreToCount(count);
    }
}
