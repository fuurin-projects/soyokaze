package soyokaze

import kotlinx.browser.document
import kotlinx.browser.window
import org.w3c.dom.CanvasRenderingContext2D
import org.w3c.dom.HTMLCanvasElement
import soyokaze.controller.Keyboard
import soyokaze.loader.IconLoader
import soyokaze.loader.SceneLoader
import soyokaze.loader.SpriteLoader
import soyokaze.loader.WorldLoader
import soyokaze.renderer.GameRenderer
import soyokaze.renderer.IconManager
import soyokaze.renderer.SpriteManager
import soyokaze.scene.SceneManager
import soyokaze.scene.world.WorldManager
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

    private lateinit var worldLoader: WorldLoader

    private lateinit var sceneLoader: SceneLoader

    private lateinit var iconLoader: IconLoader

    lateinit var worldManager: WorldManager

    lateinit var spriteManager: SpriteManager

    lateinit var iconManager: IconManager


    suspend fun init() {

        sceneManager = SceneManager()
        sceneManager.init()

        worldManager = WorldManager()

        spriteManager = SpriteManager()
        spriteManager.init()

        iconManager = IconManager()
        iconManager.init()

        keyboard = Keyboard()

        gameRenderer = GameRenderer(this)

        val scene = document.querySelector("#scene001") as HTMLCanvasElement
        val context: CanvasRenderingContext2D = scene.getContext("2d")!! as CanvasRenderingContext2D
        gameRenderer.init(context)

        spriteLoader = SpriteLoader()
        spriteManager.postInit(spriteLoader.load())

        worldLoader = WorldLoader()
        worldManager.postInit(worldLoader.load())

        sceneLoader = SceneLoader()
        sceneManager.postInit(sceneLoader.load())

        iconLoader = IconLoader()
        iconManager.postInit(iconLoader.load(spriteManager))

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

        //読み込み完了
        sceneManager.nextScene("start")

    }

}