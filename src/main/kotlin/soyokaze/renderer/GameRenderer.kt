package soyokaze.renderer

import org.w3c.dom.CanvasRenderingContext2D
import org.w3c.dom.Image
import soyokaze.Soyokaze
import soyokaze.renderer.map.Icon
import soyokaze.renderer.map.drawMapTip

class GameRenderer(private val soyokaze: Soyokaze) {

    private lateinit var background: CanvasRenderingContext2D

    private lateinit var sprite: Image

    private lateinit var kusa: Icon

    fun init(_background: CanvasRenderingContext2D) {

        background = _background;
        //ドット絵を引き伸ばさないように
        background.imageSmoothingEnabled = false

        sprite = Image();
        sprite.src = "/gamedata/resources/sprite001.png";

        kusa = Icon(sprite, 0, 0, 16, 16)

    }

    fun draw() {

        //削除
        background.clearRect(0.0, 0.0, background.canvas.width.toDouble(), background.canvas.height.toDouble())

        for (x in 0 until RendererConst.width / RendererConst.imageScale) {
            for (y in 0 until RendererConst.height / RendererConst.imageScale) {
                background.drawMapTip(
                    kusa,
                    (x * RendererConst.imageScale).toDouble(),
                    (y * RendererConst.imageScale).toDouble(),
                    RendererConst.imageScale.toDouble(),
                    RendererConst.imageScale.toDouble()
                )
            }
        }

        soyokaze.sceneManager.globalScene.draw(background)
        soyokaze.sceneManager.currentScene.draw(background)

    }

}