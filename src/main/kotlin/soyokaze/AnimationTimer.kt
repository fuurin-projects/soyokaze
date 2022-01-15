package soyokaze

import kotlinx.browser.window

class AnimationTimer(private val callback: (timer: AnimationTimer) -> Unit) {

    private var lastTime: Double = 0.0;

    var lastFPS: Double = 0.0
        private set

    fun start() {

        window.requestAnimationFrame(::tick)

    }

    private fun tick(timestamp: Double) {

        //FPSの計算
        val time = timestamp - lastTime
        lastFPS = 1 / (time / 1000)
        lastTime = timestamp

        callback(this)

        window.requestAnimationFrame(::tick);

    }

    fun stop() {

    }

}