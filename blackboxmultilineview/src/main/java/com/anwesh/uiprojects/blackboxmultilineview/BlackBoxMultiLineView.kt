package com.anwesh.uiprojects.blackboxmultilineview

/**
 * Created by anweshmishra on 22/05/19.
 */

import android.view.View
import android.view.MotionEvent
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.app.Activity
import android.content.Context
import android.graphics.RectF

val nodes : Int = 5
val lines : Int = 4
val scGap : Float = 0.05f
val scDiv : Double = 0.51
val strokeFactor : Int = 90
val sizeFactor : Float = 2.9f
val foreColor : Int = Color.BLACK
val backColor : Int = Color.parseColor("#BDBDBD")

fun Int.inverse() : Float = 1f / this
fun Float.scaleFactor() : Float = Math.floor(this / scDiv).toFloat()
fun Float.maxScale(i : Int, n : Int) : Float = Math.max(0f, this - i * n.inverse())
fun Float.divideScale(i : Int, n : Int) : Float = Math.min(n.inverse(), maxScale(i, n)) * n
fun Float.mirrorValue(a : Int, b : Int) : Float {
    val k : Float = scaleFactor()
    return (1 - k) * a.inverse() + k * b.inverse()
}
fun Float.updateValue(dir : Float, a : Int, b : Int) : Float = mirrorValue(a, b) * dir * scGap

fun Canvas.drawBlackBox(sc : Float, x : Float, size : Float, paint : Paint) {
    val boxSize : Float = (size / 2) * sc
    save()
    translate(x, 0f)
    paint.style = Paint.Style.STROKE
    drawRect(RectF(-size / 2, -size / 2, size / 2, size / 2), paint)
    paint.style = Paint.Style.FILL
    drawRect(RectF(-boxSize, -boxSize, boxSize, boxSize), paint)
    restore()
}

fun Canvas.drawMultiLine(i : Int, sc : Float, size : Float, paint : Paint) {
    val yGap : Float = size / (lines + 1)
    val x : Float = size / 3
    val sci : Float = sc.divideScale(i, lines)
    val lineSize : Float = (size / 3) * 2
    save()
    translate(x, -size / 2 + yGap / 2 + yGap * i)
    drawLine(0f, 0f, lineSize * sci, 0f, paint)
    restore()
}

fun Canvas.drawBBMLNode(i : Int, scale : Float, paint : Paint) {
    val w : Float = width.toFloat()
    val h : Float = height.toFloat()
    val gap : Float = h / (nodes + 1)
    val size : Float = gap / sizeFactor
    paint.color = foreColor
    paint.strokeWidth = Math.min(w, h) / strokeFactor
    paint.strokeCap = Paint.Cap.ROUND
    val sc1 : Float = scale.divideScale(0, 2)
    val sc2 : Float = scale.divideScale(1, 2)
    save()
    translate(w / 2, gap * (i + 1))
    drawBlackBox(sc2, -size / 2, size, paint)
    for (j in 0..(lines - 1)) {
        drawMultiLine(j, sc1, size, paint)
    }
    restore()
}

class BlackBoxMultiLineView(ctx : Context) : View(ctx) {

    private val paint : Paint = Paint(Paint.ANTI_ALIAS_FLAG)

    override fun onDraw(canvas : Canvas) {

    }

    override fun onTouchEvent(event : MotionEvent) : Boolean {
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {

            }
        }
        return true
    }
} 