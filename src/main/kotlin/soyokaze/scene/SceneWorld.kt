package soyokaze.scene

import org.w3c.dom.CanvasRenderingContext2D
import soyokaze.Soyokaze
import soyokaze.renderer.RendererConst
import soyokaze.renderer.map.drawMapTip

class SceneWorld(val name: String, val data: String) : Scene {

    override fun draw(soyokaze: Soyokaze, context: CanvasRenderingContext2D) {
        super.draw(soyokaze, context)

        val world = soyokaze.worldManager.worldList[data]!!

        for ((indexY, valueY) in world.imageData.withIndex()) {

            for ((indexX, valueX) in valueY.withIndex()) {
                
                context.drawMapTip(
                    soyokaze.iconManager.iconList[world.imageMapping[valueX]]!!,
                    (indexX * RendererConst.imageScale).toDouble(),
                    (indexY * RendererConst.imageScale).toDouble(),
                    RendererConst.imageScale.toDouble(),
                    RendererConst.imageScale.toDouble()
                )
            }
        }

    }

}