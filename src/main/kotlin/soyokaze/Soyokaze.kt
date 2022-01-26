package soyokaze

import kotlinx.browser.document
import kotlinx.browser.window
import org.w3c.dom.CanvasRenderingContext2D
import org.w3c.dom.HTMLCanvasElement
import soyokaze.controller.Keyboard
import soyokaze.event.EventManager
import soyokaze.loader.*
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

    val gameLoading: GameLoading = GameLoading()

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

    private lateinit var eventLoader: EventLoader

    lateinit var worldManager: WorldManager

    lateinit var spriteManager: SpriteManager

    lateinit var iconManager: IconManager

    lateinit var eventManager: EventManager


    suspend fun init() {

        sceneManager = SceneManager(this)
        sceneManager.init()

        worldManager = WorldManager()

        spriteManager = SpriteManager()
        spriteManager.init()

        iconManager = IconManager()
        iconManager.init()

        eventManager = EventManager()

        keyboard = Keyboard()

        gameRenderer = GameRenderer(this)

        val scene = document.querySelector("#scene001") as HTMLCanvasElement
        val context: CanvasRenderingContext2D = scene.getContext("2d")!! as CanvasRenderingContext2D
        gameRenderer.init(context)

        //各種タイマーのスタート
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

        //各種リソースのロード
        sceneManager.nextScene("game_loading")

        spriteLoader = SpriteLoader()
        spriteManager.postInit(spriteLoader.load { itemName, count, maxCount ->

            gameLoading.loadItem(
                itemName = itemName,
                count = count,
                maxCount = maxCount
            )

        })

        worldLoader = WorldLoader()
        worldManager.postInit(worldLoader.load())

        sceneLoader = SceneLoader()
        sceneManager.postInit(sceneLoader.load())

        iconLoader = IconLoader()
        iconManager.postInit(iconLoader.load(spriteManager))

        eventLoader = EventLoader()
        eventManager.postInit(eventLoader.load())

        console.log("init")


        //読み込み完了
        sceneManager.nextScene("start")

    }

}