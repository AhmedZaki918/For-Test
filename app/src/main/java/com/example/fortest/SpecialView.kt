package com.example.fortest

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import kotlin.math.min

class SpecialView(context: Context?, attrs: AttributeSet?) : View(context, attrs) {

    companion object {
        private const val DEFAULT_COLOR = Color.BLACK
    }


    /**
     *    Setup default values of the XML attribute properties,
     *    in case a user of the custom view does not set one of them
     */
    private var color = DEFAULT_COLOR


    // Paint object for coloring and styling, size
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private var size = 300

    init {
        paint.isAntiAlias = true
        setupAttributes(attrs)
    }


    // To create custom attributes
    private fun setupAttributes(attrs: AttributeSet?) {
        // Obtain a typed array of attributes
        val typedArray = context.theme.obtainStyledAttributes(
            attrs, R.styleable.SpecialView,
            0, 0
        )
        // Extract custom attributes into member variables
        color = typedArray.getColor(R.styleable.SpecialView_color, DEFAULT_COLOR)
        // Recycle the typedArray to make the data associated with it ready for garbage collection
        typedArray.recycle()
    }


    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        // Set the paint color to the faceColor and make it fill the drawing area.
        paint.color = color
        paint.style = Paint.Style.FILL
        // Calculate a radius for a circle which you want to draw as the face background
        val radius = size / 2f

        // Draw the background circle with a center of (x,y),
        // where x and y are equal to the half of size, and with the calculated radius
        canvas.drawCircle(size / 2f, size / 2f, radius, paint)
    }


    // To create responsive view, we should override this method
    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        // Calculate the smaller dimension of your view
        size = min(measuredWidth, measuredHeight)
        // to store the measured width and measured height of the view
        setMeasuredDimension(size, size)
    }
}