package com.wallace.segmentedprogressbar;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;
import androidx.annotation.ColorInt;
import androidx.annotation.Nullable;

public class SegmentedProgressBar extends View {
    private Paint containerRectanglePaint;
    private Paint fillRectanglePaint;
    private int lastCompletedSegment;
    private int currentSegmentProgressInPx;

    private int segmentCount;
    private int containerColor;
    private int fillColor;
    private int segmentGapWidth;
    private int cornerRadius;

    private static int DEFAULT_SEGMENT_COUNT = 5;
    private static int DEFAULT_CORNER_RADIUS_DP = 6;
    private static int DEFAULT_SEGMENT_GAP_DP = 1;

    public SegmentedProgressBar(Context context) {
        super(context);
        initView();
    }
    public SegmentedProgressBar(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    private void initView() {
        segmentCount = DEFAULT_SEGMENT_COUNT;
        containerColor = Color.LTGRAY;
        fillColor = Color.BLUE;
        segmentGapWidth = getDP(getContext(), DEFAULT_SEGMENT_GAP_DP);
        cornerRadius = getDP(getContext(), DEFAULT_CORNER_RADIUS_DP);
        containerRectanglePaint = buildContainerRectanglePaint(containerColor);
        fillRectanglePaint = buildFillRectanglePaint(fillColor);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawContainerRectangles(canvas);
        drawCompletedRectangles(canvas);
        drawCurrentRectangle(canvas);
    }

    private void drawContainerRectangles(Canvas canvas) {
        int segmentWidth = getSegmentWidth();

        int leftX = 0;
        int rightX = leftX + segmentWidth;
        int topY = 0;
        int botY = getHeight();

        for (int i = 0; i < segmentCount; i++) {
            drawRoundedRect(canvas, (float) leftX, (float) topY, (float) rightX, (float) botY, containerRectanglePaint);
            leftX += segmentWidth + segmentGapWidth;
            rightX = leftX + segmentWidth;
        }
    }

    private void drawCompletedRectangles(Canvas canvas) {
        int segmentWidth = getSegmentWidth();

        int leftX = 0;
        int rightX = leftX + segmentWidth;
        int topY = 0;
        int botY = getHeight();

        for (int i = 0; i < lastCompletedSegment; i++) {
            drawRoundedRect(canvas, (float) leftX, (float) topY, (float) rightX, (float) botY, fillRectanglePaint);
            leftX += segmentWidth + segmentGapWidth;
            rightX = leftX + segmentWidth;
        }
    }

    private void drawCurrentRectangle(Canvas canvas) {
        int segmentWidth = getSegmentWidth();

        int leftX = lastCompletedSegment * (segmentWidth + segmentGapWidth);
        int rightX = leftX + currentSegmentProgressInPx;
        int topY = 0;
        int botY = getHeight();
        drawRoundedRect(canvas, (float) leftX, (float) topY, (float) rightX, (float) botY, fillRectanglePaint);
    }

    private void drawRoundedRect(
        Canvas canvas,
        Float left,
        Float top,
        Float right,
        Float bottom,
        Paint paint
    ) {
        Path path = new Path();
        float rx = (float) cornerRadius;
        if (rx < 0) rx = 0f;
        float ry = 6f;
        if (ry < 0) ry = 0f;
        float width = right - left;
        float height = bottom - top;
        if (rx > width / 2) rx = width / 2;
        if (ry > height / 2) ry = height / 2;
        float widthMinusCorners = width - 2 * rx;
        float heightMinusCorners = height - 2 * ry;

        path.moveTo(right, top + ry);
        path.rQuadTo(0f, -ry, -rx, -ry); // top-right corner
        path.rLineTo(-widthMinusCorners, 0f);
        path.rQuadTo(-rx, 0f, -rx, ry); // top-left corner
        path.rLineTo(0f, heightMinusCorners);

        path.rQuadTo(0f, ry, rx, ry); // bottom-left corner
        path.rLineTo(widthMinusCorners, 0f);
        path.rQuadTo(rx, 0f, rx, -ry); // bottom-right corner

        path.rLineTo(0f, -heightMinusCorners);

        path.close();

        canvas.drawPath(path, paint);
    }

    private Paint buildFillRectanglePaint(@ColorInt int color) {
        Paint paint = new Paint();
        paint.setColor(color);
        paint.setStyle(Paint.Style.FILL);
        return paint;
    }

    private Paint buildContainerRectanglePaint(@ColorInt int color) {
        Paint paint = new Paint();
        paint.setColor(color);
        paint.setStyle(Paint.Style.FILL);
        return paint;
    }

    private int getSegmentWidth() {
        return getWidth() / segmentCount - segmentGapWidth;
    }

    private int getDP(Context context, int valueInDp) {
        return (int) (valueInDp * context.getResources().getDisplayMetrics().density);
    }

    public void setContainerColor(@ColorInt int color) {
        containerRectanglePaint = buildContainerRectanglePaint(color);
    }

    public void setFillColor(@ColorInt int color) {
        fillRectanglePaint = buildFillRectanglePaint(color);
    }

    public void setSegmentCount(int segmentCount) {
        this.segmentCount = segmentCount;
    }

    public void setSegmentGapWidth(int segmentGapWidth) {
        this.segmentGapWidth = segmentGapWidth;
    }

    public void setCornerRadius(int cornerRadius) {
        this.cornerRadius = cornerRadius;
    }

    public void setCompletedSegments(int completedSegments) {
        if (completedSegments <= segmentCount) {
            currentSegmentProgressInPx = 0;
            lastCompletedSegment = completedSegments;
            invalidate();
        }
    }
}
