package soyokaze.scene

import org.w3c.dom.CanvasRenderingContext2D
import org.w3c.dom.Image
import soyokaze.event.Hero

/**
 * 常に動作しているScene
 */
class GlobalScene : Scene {

    private lateinit var hero: Hero
    private lateinit var sprite: Image

    override fun start() {
        super.start()

        hero = Hero()
        hero.init()

        sprite = Image();
        sprite.src = "/gamedata/resources/sprite001.png";

    }

    override fun tick() {
        super.tick()

        hero.update()

    }

    override fun draw(context: CanvasRenderingContext2D) {
        super.draw(context)

        hero.drawImage(context, sprite)


    }

}