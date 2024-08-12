package com.rekoj134.moneymanagement.presentation.home

import android.app.ProgressDialog.show
import android.content.Context
import android.graphics.Canvas
import android.graphics.Path
import android.util.AttributeSet
import androidx.annotation.DrawableRes
import androidx.vectordrawable.graphics.drawable.AnimatedVectorDrawableCompat
import com.rekoj134.moneymanagement.R

class ReverseAnimatedVectorDrawableImageView : androidx.appcompat.widget.AppCompatImageView {
    private var avdSecondToFirst: AnimatedVectorDrawableCompat? = null
    private var avdFirstToSecond: AnimatedVectorDrawableCompat? = null
    private var showingAvdFirst = false

    constructor(context: Context) : super(context) {
        initView(context, null)
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        initView(context, attrs)
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        initView(context, attrs)
    }

    private fun initView(context: Context, attrs: AttributeSet?) {
        val a = context.theme.obtainStyledAttributes(
            attrs,
            R.styleable.ReverseAnimatedVectorDrawableImageView,
            0, 0
        )

        @DrawableRes val avdFromRes: Int
        @DrawableRes val avdToRes: Int
        try {
            avdFromRes = a.getResourceId(R.styleable.ReverseAnimatedVectorDrawableImageView_avdFirst, -1)
            avdToRes = a.getResourceId(R.styleable.ReverseAnimatedVectorDrawableImageView_avdSecond, -1)
        } finally {
            a.recycle()
        }

        showingAvdFirst = true
        avdFirstToSecond = AnimatedVectorDrawableCompat.create(getContext(), avdFromRes)
        avdSecondToFirst = AnimatedVectorDrawableCompat.create(getContext(), avdToRes)

        if (avdSecondToFirst == null || avdFirstToSecond == null) {
            throw RuntimeException("Drawable is not a valid AnimatedVectorDrawable")
        } else {
            setImageDrawable(avdFirstToSecond)
        }
    }

    fun startAnim(onDone:() -> Unit) {
        val drawable = if (showingAvdFirst) avdFirstToSecond else avdSecondToFirst
        setImageDrawable(drawable)
        drawable?.start()
        onDone.invoke()
        showingAvdFirst = !showingAvdFirst
    }

    fun setAvdFirst(avdFirst: AnimatedVectorDrawableCompat) {
        avdFirstToSecond = avdFirst
        invalidate()
    }

    fun setAvdSecond(avdSecond: AnimatedVectorDrawableCompat) {
        avdSecondToFirst = avdSecond
        invalidate()
    }
}