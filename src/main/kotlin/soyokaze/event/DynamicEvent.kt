package soyokaze.event

import org.w3c.dom.CanvasRenderingContext2D
import soyokaze.Soyokaze
import soyokaze.event.node.Node
import soyokaze.event.node.input.InputNode
import soyokaze.event.node.middle.MiddleNode
import soyokaze.event.node.output.OutputNode
import soyokaze.renderer.RendererConst
import soyokaze.renderer.map.drawMapTip

/**
 * 移動するEvent
 */
class DynamicEvent(
    val inputNodeList: List<InputNode>,
    val middleNodeList: List<MiddleNode>,
    val outputNodeList: List<OutputNode>,
    val imageMapping: Map<String, String>
) : Event {

    private var posX: Double = 0.0
    private var posY: Double = 0.0

    private var angle: Int = 0

    private val values = mutableMapOf<String, Any>()

    private var image = ""

    override fun <T> setValue(key: String, value: T) {

        if (key == "image") {
            image = value as String
        } else {
            values[key] = value as Any
        }

    }

    override fun getNode(id: String): Node? {
        for (inputNode in inputNodeList) {
            if (inputNode.getId() == id) {
                return inputNode
            }
        }
        for (middleNode in middleNodeList) {
            if (middleNode.getId() == id) {
                return middleNode
            }
        }
        return null
    }

    override fun tick(soyokaze: Soyokaze) {

        for (inputNode in inputNodeList) {
            inputNode.tick(soyokaze, this)
        }

        for (middleNode in middleNodeList) {
            middleNode.tick(soyokaze, this)
        }

        for (outputNode in outputNodeList) {
            outputNode.tick(soyokaze, this)
        }

        if (values[EventConst.MOVE_X] != null) {
            posX += values[EventConst.MOVE_X].toString().toDouble() * 2
        }
        if (values[EventConst.MOVE_Y] != null) {
            posY += values[EventConst.MOVE_Y].toString().toDouble() * 2
        }

    }

    override fun drawImage(soyokaze: Soyokaze, context: CanvasRenderingContext2D) {

        context.save()
        context.beginPath();
        context.rect(posX, posY, RendererConst.imageScale.toDouble(), RendererConst.imageScale.toDouble())
        context.strokeStyle = "blue";
        context.lineWidth = 1.0;
        context.stroke()
        context.restore()

        if (image != "") {
            context.drawMapTip(
                soyokaze.iconManager.iconList[imageMapping[image]]!!,
                posX, posY, RendererConst.imageScale.toDouble(), RendererConst.imageScale.toDouble()
            )
        }

    }


}