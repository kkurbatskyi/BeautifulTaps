package com.example.beautifultaps

import android.graphics.Canvas
import android.view.SurfaceHolder

class MainThread(_surfaceHolder: SurfaceHolder?, _gameView: GameView?): Thread() {
    private var surfaceHolder: SurfaceHolder? = _surfaceHolder
    private var gameView: GameView? = _gameView

    private var running = false
    var canvas: Canvas? = null

    fun setRunning(isRunning: Boolean) {
        running = isRunning
    }

    override fun run() {
        while (running) {
            canvas = null
            try {
                canvas = surfaceHolder!!.lockCanvas()
                synchronized(surfaceHolder!!) {
                    gameView?.update()
                    gameView!!.draw(canvas)
                }
            }
            catch (e: Exception) {}
            finally {
                if (canvas != null) {
                    try {
                        surfaceHolder!!.unlockCanvasAndPost(canvas)
                    } catch (e: Exception) { e.printStackTrace() }
                }
            }
        }
    }
}