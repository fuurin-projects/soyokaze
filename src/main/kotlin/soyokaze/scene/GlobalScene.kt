package soyokaze.scene

import org.w3c.dom.CanvasRenderingContext2D
import org.w3c.dom.Image
import soyokaze.Soyokaze
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

    override fun tick(soyokaze: Soyokaze) {
        super.tick(soyokaze)

        hero.update()

        val eventGlobalList = soyokaze.eventManager.eventGlobalList

        for (entry in eventGlobalList) {

            entry.value.tick(soyokaze = soyokaze)

        }

    }

    override fun draw(soyokaze: Soyokaze, context: CanvasRenderingContext2D) {
        super.draw(soyokaze, context)

        hero.drawImage(context, sprite)

        val eventGlobalList = soyokaze.eventManager.eventGlobalList

        for (entry in eventGlobalList) {

            entry.value.drawImage(soyokaze = soyokaze, context = context)

        }


    }

}