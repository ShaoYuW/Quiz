package com.yucheng.connexiononequiz


import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.widget.FrameLayout

class RoundFrameLayout @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    private var topLeftRadius = -20f
    private var topRightRadius = -20f
    private var bottomLeftRadius = -20f
    private var bottomRightRadius = -20f
    private var borderEnable = false

    init {
        if (attrs != null) {
            val ta = context.obtainStyledAttributes(attrs, R.styleable.RoundFrameLayout)
            val radius = ta.getDimension(R.styleable.RoundFrameLayout_radius, 0f)
            topLeftRadius = ta.getDimension(R.styleable.RoundFrameLayout_topLeftRadius, radius)
            topRightRadius = ta.getDimension(R.styleable.RoundFrameLayout_topRightRadius, radius)
            bottomLeftRadius = ta.getDimension(R.styleable.RoundFrameLayout_bottomLeftRadius, radius)
            bottomRightRadius = ta.getDimension(R.styleable.RoundFrameLayout_bottomRightRadius, radius)
            borderEnable = ta.getBoolean(R.styleable.RoundFrameLayout_borderEnable, false)
            ta.recycle()
        }
    }

    fun setRadius(radius: Float) {
        val r = radius.dpToPixel(context)
        topLeftRadius = r
        topRightRadius = r
        bottomLeftRadius = r
        bottomRightRadius = r
        postInvalidate()
    }

    fun setTopRadius(radius: Float){
        radius.dpToPixel(context).let {
            topLeftRadius = it
            topRightRadius = it
        }
        invalidate()
    }

    fun setBottomRadius(radius: Float){
        radius.dpToPixel(context).let {
            bottomLeftRadius = it
            bottomRightRadius = it
        }
        invalidate()
    }

    private val roundPaint by lazy {
        Paint(Paint.ANTI_ALIAS_FLAG).apply {
            color = Color.WHITE
            style = Paint.Style.FILL
            xfermode = PorterDuffXfermode(PorterDuff.Mode.DST_OUT)
        }
    }

    private val imagePaint by lazy {
        Paint().apply {
            xfermode = null
        }
    }

    override fun dispatchDraw(canvas: Canvas) {
        canvas.saveLayer(RectF(0f, 0f, canvas.width.toFloat(), canvas.height.toFloat()), imagePaint, Canvas.ALL_SAVE_FLAG)
        super.dispatchDraw(canvas)
        drawBorder(canvas)
        drawTopLeft(canvas)
        drawTopRight(canvas)
        drawBottomLeft(canvas)
        drawBottomRight(canvas)
        canvas.restore()
    }

    private fun drawBorder(canvas: Canvas) {
        if (!borderEnable) {
            return
        }
        val path = Path()
        if (topLeftRadius > 0) {
            path.arcTo(RectF(0f, 0f, (topLeftRadius * 2), (topLeftRadius * 2)),
                180f, 90f, false)
        } else if (topLeftRadius < 0) {
            path.arcTo(RectF(topLeftRadius, topLeftRadius, (-topLeftRadius), (-topLeftRadius)),
                90f, -90f, false)
        }


        if (topRightRadius > 0) {
            path.arcTo(RectF((width - 2 * topRightRadius), 0f, width.toFloat(),
                (topRightRadius * 2)), -90f, 90f, false)
        } else if (topRightRadius < 0) {
            path.arcTo(RectF((width + topRightRadius), topRightRadius, width.toFloat() - topRightRadius, (-topRightRadius)),
                -180f, -90f, false)
        }


        if (bottomRightRadius > 0) {
            path.arcTo(RectF((width - 2 * bottomRightRadius), (height - 2 * bottomRightRadius), width.toFloat(), height.toFloat()),
                0f, 90f, false)
        } else if (bottomRightRadius < 0) {
            path.arcTo(RectF((width + bottomRightRadius), (height + bottomRightRadius), width.toFloat() - bottomRightRadius, height.toFloat() - bottomRightRadius),
                -90f, -90f, false)
        }


        if (bottomLeftRadius > 0) {
            path.arcTo(RectF(0f, (height - 2 * bottomLeftRadius),
                (bottomLeftRadius * 2), height.toFloat()), 90f, 90f, false)
        } else if (bottomLeftRadius < 0) {
            path.arcTo(RectF(bottomLeftRadius, (height + bottomLeftRadius), (-bottomLeftRadius), height.toFloat() - bottomLeftRadius),
                0f, -90f, false)
        }

        path.close()

        canvas.drawPath(path, borderPaint)
    }

    private val borderPaint by lazy {
        Paint(Paint.ANTI_ALIAS_FLAG).apply {
            color = Color.WHITE
            strokeWidth = 10f
            style = Paint.Style.STROKE
            isAntiAlias = true
            isDither = true
        }
    }

    private fun drawTopLeft(canvas: Canvas) {
        if (topLeftRadius > 0) {
            val path = Path()
            path.moveTo(0f, topLeftRadius)
            path.lineTo(0f, 0f)
            path.lineTo(topLeftRadius, 0f)
            path.arcTo(RectF(0f, 0f, (topLeftRadius * 2), (topLeftRadius * 2)),
                -90f, -90f)
            path.close()
            canvas.drawPath(path, roundPaint)
        } else if (topLeftRadius < 0) {
            val path = Path()
            path.moveTo(0f, -topLeftRadius)
            path.lineTo(0f, 0f)
            path.lineTo(-topLeftRadius, 0f)
            path.arcTo(RectF(topLeftRadius, topLeftRadius, (-topLeftRadius), (-topLeftRadius)),
                0f, 90f)
            path.close()
            canvas.drawPath(path, roundPaint)
        }
    }

    private fun drawTopRight(canvas: Canvas) {
        if (topRightRadius > 0f) {
            val width = width
            val path = Path()
            path.moveTo(width - topRightRadius, 0f)
            path.lineTo(width.toFloat(), 0f)
            path.lineTo(width.toFloat(), topRightRadius)
            path.arcTo(RectF((width - 2 * topRightRadius), 0f, width.toFloat(),
                (topRightRadius * 2)), 0f, -90f)
            path.close()
            canvas.drawPath(path, roundPaint)
        } else if (topRightRadius < 0f) {
            val width = width
            val path = Path()
            path.moveTo(width + topRightRadius, 0f)
            path.lineTo(width.toFloat(), 0f)
            path.lineTo(width.toFloat(), -topRightRadius)
            path.arcTo(RectF((width + topRightRadius), topRightRadius, width.toFloat() - topRightRadius,
                (-topRightRadius)), -180f, -90f)
            path.close()
            canvas.drawPath(path, roundPaint)
        }
    }

    private fun drawBottomLeft(canvas: Canvas) {
        if (bottomLeftRadius > 0f) {
            val height = height
            val path = Path()
            path.moveTo(0f, height - bottomLeftRadius)
            path.lineTo(0f, height.toFloat())
            path.lineTo(bottomLeftRadius, height.toFloat())
            path.arcTo(RectF(0f, (height - 2 * bottomLeftRadius),
                (bottomLeftRadius * 2), height.toFloat()), 90f, 90f)
            path.close()
            canvas.drawPath(path, roundPaint)
        } else if (bottomLeftRadius < 0f) {
            val height = height
            val path = Path()
            path.moveTo(0f, height + bottomLeftRadius)
            path.lineTo(0f, height.toFloat())
            path.lineTo(-bottomLeftRadius, height.toFloat())
            path.arcTo(RectF(bottomLeftRadius, (height + bottomLeftRadius),
                (-bottomLeftRadius), height.toFloat() - bottomLeftRadius), 0f, -90f)
            path.close()
            canvas.drawPath(path, roundPaint)
        }
    }

    private fun drawBottomRight(canvas: Canvas) {
        if (bottomRightRadius > 0f) {
            val height = height
            val width = width
            val path = Path()
            path.moveTo(width - bottomRightRadius, height.toFloat())
            path.lineTo(width.toFloat(), height.toFloat())
            path.lineTo(width.toFloat(), height - bottomRightRadius)
            path.arcTo(RectF((width - 2 * bottomRightRadius), (height - 2 * bottomRightRadius), width.toFloat(), height.toFloat()), 0f, 90f)
            path.close()
            canvas.drawPath(path, roundPaint)
        }

        if (bottomRightRadius < 0f) {
            val height = height
            val width = width
            val path = Path()
            path.moveTo(width - bottomRightRadius, height.toFloat())
            path.lineTo(width.toFloat(), height.toFloat())
            path.lineTo(width.toFloat(), height - bottomRightRadius)
            path.arcTo(RectF((width + bottomRightRadius), (height + bottomRightRadius), width.toFloat() - bottomRightRadius, height.toFloat() - bottomRightRadius), -90f, -90f)
            path.close()
            canvas.drawPath(path, roundPaint)
        }
    }

    private fun Float.dpToPixel(context: Context): Float {
        return this * context.resources.displayMetrics.density
    }
}