package soyokaze

import kotlinx.browser.document
import kotlinx.browser.window
import org.w3c.dom.CanvasRenderingContext2D
import org.w3c.dom.HTMLCanvasElement
import soyokaze.controller.Keyboard
import soyokaze.loader.SpriteLoader
import soyokaze.renderer.GameRenderer
import soyokaze.scene.SceneManager
import kotlin.math.round

/**
 * ゲームエンジンのメインクラス
 */
class Soyokaze {

    lateinit var sceneManager: SceneManager
        private set

    private lateinit var systemTimer: SystemTimer
    private lateinit var animationTimer: AnimationTimer

    lateinit var keyboard: Keyboard
        private set

    private lateinit var gameRenderer: GameRenderer

    private lateinit var spriteLoader: SpriteLoader


    suspend fun init() {

        sceneManager = SceneManager()
        sceneManager.init()

        keyboard = Keyboard()

        gameRenderer = GameRenderer(this)

        val scene = document.querySelector("#scene001") as HTMLCanvasElement
        val context: CanvasRenderingContext2D = scene.getContext("2d")!! as CanvasRenderingContext2D
        gameRenderer.init(context)

        spriteLoader = SpriteLoader()
        spriteLoader.load()

        systemTimer = SystemTimer(30) {

            sceneManager.tick()
        }
        systemTimer.start()

        animationTimer = AnimationTimer {
            gameRenderer.draw()
        }
        animationTimer.start()

        window.setInterval({
            window.document.querySelector("#fps")!!.textContent =
                "TPS: ${round(systemTimer.lastFPS)}, FPS: ${round(animationTimer.lastFPS)}"
        }, 200)

        console.log("init")

    }

}