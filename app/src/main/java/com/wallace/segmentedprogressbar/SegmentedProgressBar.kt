package com.wallace.segmentedprogressbar
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.View
import androidx.annotation.ColorInt

//class SegmentedProgressBar : View {
//
//    private lateinit var containerRectanglePaint: Paint
//    private lateinit var fillRectanglePaint: Paint
//    private var lastCompletedSegment = 0
//    private var currentSegmentProgressInPx = 0
//
//    private var segmentCount: Int = DEFAULT_SEGMENT_COUNT
//    private var containerColor: Int = Color.LTGRAY
//    private var fillColor: Int = Color.BLUE
//    var segmentGapWidth: Int = getDP(context, DEFAULT_SEGMENT_GAP_DP)
//    var cornerRadius: Int = getDP(context, DEFAULT_CORNER_RADIUS_DP)
//
//    companion object {
//        const val DEFAULT_SEGMENT_COUNT = 5
//        const val DEFAULT_CORNER_RADIUS_DP = 6
//        const val DEFAULT_SEGMENT_GAP_DP = 1
//    }
//
//    constructor(context: Context) : super(context) {
//        initView()
//    }
//
//    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
//        initView()
//    }
//
//    private fun initView() {
//        containerRectanglePaint = buildContainerRectanglePaint(containerColor)
//        fillRectanglePaint = buildFillRectanglePaint(fillColor)
//    }
//
//    override fun onDraw(canvas: Canvas) {
//        super.onDraw(canvas)
//        drawContainerRectangles(canvas)
//        drawCompletedRectangles(canvas)
//        drawCurrentRectangle(canvas)
//    }
//
//    fun setContainerColor(@ColorInt color: Int) {
//        containerRectanglePaint = buildContainerRectanglePaint(color)
//    }
//
//    fun setFillColor(@ColorInt color: Int) {
//        fillRectanglePaint = buildFillRectanglePaint(color)
//    }
//
//    fun setSegmentCount(segmentCount: Int) {
//        this.segmentCount = segmentCount
//    }
//
//    fun setCompletedSegments(completedSegments: Int) {
//        if (completedSegments <= segmentCount) {
//            currentSegmentProgressInPx = 0
//            lastCompletedSegment = completedSegments
//            invalidate()
//        }
//    }
//
//    private fun drawContainerRectangles(canvas: Canvas) {
//        val segmentWidth = segmentWidth
//
//        var leftX = 0
//        var rightX = leftX + segmentWidth
//        val topY = 0
//        val botY = height
//
//        for (i in 0 until segmentCount) {
//            drawRoundedRect(canvas, leftX.toFloat(), topY.toFloat(), rightX.toFloat(), botY.toFloat(),
//                    containerRectanglePaint)
//            leftX += segmentWidth + segmentGapWidth
//            rightX = leftX + segmentWidth
//        }
//    }
//
//    private fun drawCompletedRectangles(canvas: Canvas) {
//        val segmentWidth = segmentWidth
//
//        var leftX = 0
//        var rightX = leftX + segmentWidth
//        val topY = 0
//        val botY = height
//
//        for (i in 0 until lastCompletedSegment) {
//            drawRoundedRect(canvas, leftX.toFloat(), topY.toFloat(), rightX.toFloat(), botY.toFloat(), fillRectanglePaint)
//            leftX += segmentWidth + segmentGapWidth
//            rightX = leftX + segmentWidth
//        }
//    }
//
//    private fun drawCurrentRectangle(canvas: Canvas) {
//        val segmentWidth = segmentWidth
//
//        val leftX = lastCompletedSegment * (segmentWidth + segmentGapWidth)
//        val rightX = leftX + currentSegmentProgressInPx
//        val topY = 0
//        val botY = height
//        drawRoundedRect(canvas, leftX.toFloat(), topY.toFloat(), rightX.toFloat(), botY.toFloat(), fillRectanglePaint)
//    }
//
//    private fun drawRoundedRect(
//        canvas: Canvas,
//        left: Float,
//        top: Float,
//        right: Float,
//        bottom: Float,
//        paint: Paint
//    ) {
//        val path = Path()
//        var rx = cornerRadius.toFloat()
//        if (rx < 0) rx = 0f
//        var ry = 6f
//        if (ry < 0) ry = 0f
//        val width = right - left
//        val height = bottom - top
//        if (rx > width / 2) rx = width / 2
//        if (ry > height / 2) ry = height / 2
//        val widthMinusCorners = width - 2 * rx
//        val heightMinusCorners = height - 2 * ry
//
//        with(path) {
//            moveTo(right, top + ry)
//            rQuadTo(0f, -ry, -rx, -ry) // top-right corner
//            rLineTo(-widthMinusCorners, 0f)
//            rQuadTo(-rx, 0f, -rx, ry) // top-left corner
//            rLineTo(0f, heightMinusCorners)
//
//            rQuadTo(0f, ry, rx, ry) // bottom-left corner
//            rLineTo(widthMinusCorners, 0f)
//            rQuadTo(rx, 0f, rx, -ry) // bottom-right corner
//
//            rLineTo(0f, -heightMinusCorners)
//
//            close()
//        }
//
//        canvas.drawPath(path, paint)
//    }
//
//    private fun buildFillRectanglePaint(@ColorInt color: Int): Paint {
//        val paint = Paint()
//        paint.color = color
//        paint.style = Paint.Style.FILL
//        return paint
//    }
//
//    private fun buildContainerRectanglePaint(@ColorInt color: Int): Paint {
//        val paint = Paint()
//        paint.color = color
//        paint.style = Paint.Style.FILL
//        return paint
//    }
//
//    private val segmentWidth: Int
//        get() = width / segmentCount - segmentGapWidth
//
//    private fun getDP(context: Context, valueInDp: Int): Int = (valueInDp * context.resources.displayMetrics.density).toInt()
//}