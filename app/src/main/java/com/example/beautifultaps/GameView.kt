package com.example.beautifultaps

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.view.SurfaceHolder
import android.view.SurfaceView

class GameView(mainActivity: MainActivity) : SurfaceView(mainActivity), SurfaceHolder.Callback{
    private val thread: MainThread = MainThread(holder, this)
    //TODO: Create a variable for Sparkles

    init {
        holder.addCallback(this)
        isFocusable = true
    }

    override fun surfaceCreated(holder: SurfaceHolder) {
        thread.setRunning(true)
        thread.start()
    }

    override fun surfaceChanged(holder: SurfaceHolder, format: Int, width: Int, height: Int) {

    }

    override fun surfaceDestroyed(holder: SurfaceHolder) {
        var retry = true
        while (retry) {
            try {
                thread.setRunning(false)
                thread.join()
            } catch (e: InterruptedException) {
                e.printStackTrace()
            }
            retry = false
        }
    }

    override fun draw(canvas: Canvas?) {
        //TODO: ON TOUCH add sparkles
        super.draw(canvas)
        if (canvas != null) {
            canvas.drawColor(Color.WHITE)
            val paint = Paint()
            paint.color = Color.rgb(250, 0, 0)
            canvas.drawRect(100F, 100F, 200F, 200F, paint)
        }
    }

    fun update(){
        //TODO: move all the sparkles
    }
}
