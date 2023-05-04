package com.arash.altafi.swipebutton.progressLayout

import android.annotation.TargetApi
import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.drawable.Animatable
import android.os.Build
import android.os.Handler
import android.util.AttributeSet
import android.view.View
import com.arash.altafi.swipebutton.R

class ProgressLayout : View, Animatable {
    var isPlaying = false
        private set
    private var isAutoProgress = false
    private var mHeight = 0
    private var mWidth = 0
    private var maxProgress = 0
    private var currentProgress = 0
    private var handlerProgress: Handler? = null
    private var progressLayoutListener: ProgressLayoutListener? = null

    @JvmOverloads
    constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        init(context, attrs)
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    constructor(
        context: Context,
        attrs: AttributeSet?,
        defStyleAttr: Int,
        defStyleRes: Int
    ) : super(context, attrs, defStyleAttr, defStyleRes) {
        init(context, attrs)
    }

    override fun isRunning(): Boolean {
        return isPlaying
    }

    override fun start() {
        if (isAutoProgress) {
            isPlaying = true
            handlerProgress!!.removeCallbacksAndMessages(null)
            handlerProgress!!.postDelayed(mRunnableProgress, 0)
        }
    }

    override fun stop() {
        isPlaying = false
        handlerProgress!!.removeCallbacks(mRunnableProgress)
        postInvalidate()
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        mWidth = MeasureSpec.getSize(widthMeasureSpec)
        mHeight = MeasureSpec.getSize(heightMeasureSpec)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.drawRect(
            0f, 0f, mWidth.toFloat(), mHeight.toFloat(),
            paintProgressEmpty!!
        )
        canvas.drawRect(
            0f, 0f, calculatePositionIndex(currentProgress).toFloat(), mHeight.toFloat(),
            paintProgressLoaded!!
        )
    }

    fun init(context: Context, attrs: AttributeSet?, loadedColor: Int = 0, emptyColor: Int = 0) {
        setWillNotDraw(false)
        val a = context.obtainStyledAttributes(attrs, R.styleable.progressLayout)
        isAutoProgress = a.getBoolean(R.styleable.progressLayout_autoProgress, true)
        maxProgress = a.getInt(R.styleable.progressLayout_maxProgress, 0)
        maxProgress *= 10

//        loadedColor = a.getColor(R.styleable.progressLayout_loadedColor, COLOR_LOADED_DEFAULT)
//        emptyColor = a.getColor(R.styleable.progressLayout_emptyColor, COLOR_EMPTY_DEFAULT)

        paintProgressEmpty = Paint()
        paintProgressEmpty!!.color = emptyColor
        paintProgressEmpty!!.style = Paint.Style.FILL
        paintProgressEmpty!!.isAntiAlias = true

        paintProgressLoaded = Paint()
        paintProgressLoaded!!.color = loadedColor
        paintProgressLoaded!!.style = Paint.Style.FILL
        paintProgressLoaded!!.isAntiAlias = true
        handlerProgress = Handler()

    }

    private fun calculatePositionIndex(currentProgress: Int): Int {
        return currentProgress * mWidth / maxProgress
    }

    fun cancel() {
        isPlaying = false
        currentProgress = 0
        handlerProgress!!.removeCallbacks(mRunnableProgress)
        postInvalidate()
    }

    fun setCurrentProgress(currentProgress: Int) {
        this.currentProgress = currentProgress * 10
        postInvalidate()
    }

    fun setMaxProgress(maxProgress: Int) {
        this.maxProgress = maxProgress * 10
        postInvalidate()
    }

    fun setAutoProgress(isAutoProgress: Boolean) {
        this.isAutoProgress = isAutoProgress
    }

    fun setProgressLayoutListener(progressLayoutListener: ProgressLayoutListener?) {
        this.progressLayoutListener = progressLayoutListener
    }

    private val mRunnableProgress: Runnable = object : Runnable {
        override fun run() {
            if (isPlaying) {
                if (currentProgress == maxProgress) {
                    if (progressLayoutListener != null) {
                        progressLayoutListener!!.onProgressCompleted()
                    }
                    currentProgress = 0
                    setCurrentProgress(currentProgress)
                    stop()
                } else {
                    postInvalidate()
                    currentProgress += 1
                    if (progressLayoutListener != null) {
                        progressLayoutListener!!.onProgressChanged(currentProgress / 10)
                    }
                    handlerProgress!!.postDelayed(this, (PROGRESS_SECOND_MS / 10).toLong())
                }
            }
        }
    }

    companion object {
        private const val PROGRESS_SECOND_MS = 1000
        private var paintProgressLoaded: Paint? = null
        private var paintProgressEmpty: Paint? = null
        private const val COLOR_EMPTY_DEFAULT = 0x00000000
        private const val COLOR_LOADED_DEFAULT = 0x11FFFFFF
    }

    interface ProgressLayoutListener {
        fun onProgressCompleted()
        fun onProgressChanged(var1: Int)
    }
}
