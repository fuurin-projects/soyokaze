package soyokaze

import kotlinx.browser.window
import kotlin.js.Date

/**
 * 指定したFPSで定期実行するタイマークラス
 */
class Timer(private val fps: Int, private val callback: (timer: Timer) -> Unit) {

    private var lastTime: Double = 0.0;

    var lastFPS: Double = 0.0
        private set

    fun start() {

        window.setTimeout(::tick, 1000 / fps)

    }

    private fun tick() {

        //FPSの計算
        val now = Date().getTime()
        val time = now - lastTime
        lastFPS = 1 / (time / 1000)
        lastTime = now

        callback(this)

        window.setTimeout(::tick, 1000 / fps);

    }

    fun stop() {

    }

}