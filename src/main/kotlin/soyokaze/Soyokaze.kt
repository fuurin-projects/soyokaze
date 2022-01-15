package soyokaze

import kotlinx.browser.window
import kotlin.math.round

/**
 * ゲームエンジンのメインクラス
 */
class Soyokaze {

    private lateinit var timer: Timer;

    fun init() {

        timer = Timer(30) {


        }
        timer.start()

        window.setInterval({
            window.document.querySelector("#fps")!!.textContent = "FPS: ${round(timer.lastFPS)}"
        }, 200)

        console.log("init")

    }

}