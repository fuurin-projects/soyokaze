package soyokaze.scene

/**
 * それぞれの画面を管理するクラス
 */
class SceneManager {

    var currentScene: Scene = InitScene();
    val globalScene: Scene = GlobalScene();

    fun init() {
        currentScene.start()
        globalScene.start()
    }

    fun tick() {

        globalScene.tick()
        currentScene.tick()

    }

}