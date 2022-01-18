package soyokaze.scene

/**
 * それぞれの画面を管理するクラス
 */
class SceneManager() {

    private val sceneList = mutableMapOf<String, Scene>()

    var currentScene: Scene = InitScene();
    val globalScene: Scene = GlobalScene();

    fun init() {
        currentScene.start()

        globalScene.start()
    }

    fun postInit(sceneList: Map<String, Scene>) {
        this.sceneList += sceneList
    }

    fun nextScene(name: String) {

        console.log("nextScene: ${name}")

        val scene = sceneList[name]
        currentScene = scene!!

    }

    fun tick() {

        globalScene.tick()
        currentScene.tick()

    }

}