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
    val imageMapping: Map<String, String>,
    val stateImage: Map<String, String>
) : Event {

    private var posX: Double = 0.0
    private var posY: Double = 0.0

    private var angle: Int = 2

    //歩行アニメーション用の処理
    private val moveSize = 4 // 次のアニメーションまでに必要なtick数
    private var modeAnimationCount = 0 //アニメーション用のカウントの値
    private var modeAnimationPoint = 0 // 何枚目の画像を使うかの値

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

        //移動処理
        if (values[EventConst.MOVE_X] != null) {
            posX += values[EventConst.MOVE_X].toString().toDouble() * 2
        }
        if (values[EventConst.MOVE_Y] != null) {
            posY += values[EventConst.MOVE_Y].toString().toDouble() * 2
        }

        var isMove = false

        if (values[EventConst.MOVE_Y] != null) {

            if (values[EventConst.MOVE_Y].toString().toInt() > 0) {
                angle = 2
                isMove = true
            } else if (values[EventConst.MOVE_Y].toString().toInt() < 0) {
                angle = 0
                isMove = true
            }

        }

        if (values[EventConst.MOVE_X] != null) {

            if (values[EventConst.MOVE_X].toString().toInt() > 0) {
                angle = 1
                isMove = true
            } else if (values[EventConst.MOVE_X].toString().toInt() < 0) {
                angle = 3
                isMove = true
            }

        }


        //動いていたら歩行アニメーションを更新する
        if (isMove) {
            modeAnimationCount += 1
            if (modeAnimationCount == 4 * moveSize) {
                modeAnimationCount = 0
            }
        } else {
            //はじめの一歩のアニメーションを確実にするためにアニメーションのしきい値の-1に設定する
            modeAnimationCount = moveSize - 1
        }
        modeAnimationPoint = modeAnimationCount / moveSize

    }

    override fun drawImage(soyokaze: Soyokaze, context: CanvasRenderingContext2D) {

        context.save()
        context.beginPath();
        context.rect(posX, posY, RendererConst.imageScale.toDouble(), RendererConst.imageScale.toDouble())
        context.strokeStyle = "blue";
        context.lineWidth = 1.0;
        context.stroke()
        context.restore()

        if (stateImage.isEmpty().not()) {
            context.drawMapTip(
                soyokaze.iconManager.iconList[imageMapping[stateImage.get(getStateImageString())]]!!,
                posX, posY, RendererConst.imageScale.toDouble(), RendererConst.imageScale.toDouble()
            )
        }

    }

    fun getStateImageString(): String {
        return "${angle}_${modeAnimationPoint}"
    }


}