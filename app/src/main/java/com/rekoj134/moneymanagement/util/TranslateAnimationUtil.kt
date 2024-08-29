package com.rekoj134.moneymanagement.util

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import com.rekoj134.moneymanagement.R

class TranslateAnimationUtil(private val context: Context, private val animViewDown: View, private val animViewScale: View) : View.OnTouchListener {
    private var gestureDetector: GestureDetector = GestureDetector(context, SimpleGestureDetector(animViewDown, animViewScale))

    class SimpleGestureDetector(private val animViewDown: View, private val animViewScale: View) : GestureDetector.SimpleOnGestureListener() {
        private var isFinishAnimation = true

        override fun onScroll(
            e1: MotionEvent?,
            e2: MotionEvent,
            distanceX: Float,
            distanceY: Float
        ): Boolean {
            Log.e("ANCUTKO", "DISTANCE Y: " + distanceY.toString())
            if (distanceY > 0) {
                hideView()
            } else {
                showView()
            }
            return super.onScroll(e1, e2, distanceX, distanceY)
        }

        private fun showView() {
            if (animViewDown.visibility == View.VISIBLE) {
                return
            }

            val animUp = AnimationUtils.loadAnimation(animViewDown.context, R.anim.bottom_up)
            animUp.setAnimationListener(object : Animation.AnimationListener {
                override fun onAnimationStart(p0: Animation?) {
                    animViewDown.visibility = View.VISIBLE
                    isFinishAnimation = false
                }

                override fun onAnimationEnd(p0: Animation?) {
                    isFinishAnimation = true
                }

                override fun onAnimationRepeat(p0: Animation?) {

                }
            })

            val animScaleBigOut = AnimationUtils.loadAnimation(animViewDown.context, R.anim.big_out)
            animScaleBigOut.setAnimationListener(object : Animation.AnimationListener {
                override fun onAnimationStart(p0: Animation?) {
                    animViewDown.visibility = View.VISIBLE
                    animViewScale.visibility = View.VISIBLE
                    isFinishAnimation = false
                }

                override fun onAnimationEnd(p0: Animation?) {
                    isFinishAnimation = true
                }

                override fun onAnimationRepeat(p0: Animation?) {

                }
            })

            if (isFinishAnimation) {
                Log.e("ANCUTKO", "SHOW VIEW")
                animViewDown.startAnimation(animUp)
                animViewScale.startAnimation(animScaleBigOut)
            }
        }

        private fun hideView() {
            if (animViewDown.visibility == View.GONE) {
                return
            }

            val animDown = AnimationUtils.loadAnimation(animViewDown.context, R.anim.bottom_down)
            animDown.setAnimationListener(object : Animation.AnimationListener {
                override fun onAnimationStart(p0: Animation?) {
                    isFinishAnimation = false
                }

                override fun onAnimationEnd(p0: Animation?) {
                    animViewDown.visibility = View.GONE
                    animViewScale.visibility = View.GONE
                    isFinishAnimation = true
                }

                override fun onAnimationRepeat(p0: Animation?) {

                }
            })

            val animScaleSmallIn = AnimationUtils.loadAnimation(animViewDown.context, R.anim.small_in)
            animScaleSmallIn.setAnimationListener(object : Animation.AnimationListener {
                override fun onAnimationStart(p0: Animation?) {
                    animViewDown.visibility = View.VISIBLE
                    isFinishAnimation = false
                }

                override fun onAnimationEnd(p0: Animation?) {
                    isFinishAnimation = true
                }

                override fun onAnimationRepeat(p0: Animation?) {

                }
            })

            if (isFinishAnimation) {
                Log.e("ANCUTKO", "HIDE VIEW")
                animViewDown.startAnimation(animDown)
                animViewScale.startAnimation(animScaleSmallIn)
            }
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouch(p0: View, motionEvent: MotionEvent): Boolean {
        return gestureDetector.onTouchEvent(motionEvent)
    }
}