package soyokaze.event

import org.w3c.dom.CanvasRenderingContext2D
import org.w3c.dom.Image
import soyokaze.Soyokaze
import soyokaze.controller.Keyboard
import soyokaze.event.node.Node
import soyokaze.renderer.RendererConst

class Hero : Event {

    private var posX: Double = 0.0
    private var posY: Double = 0.0

    private var modeAnimationCount = 0
    private var modeAnimationPoint = 0
    private var firstStep = true

    private var angle = 0;

    private val moveSpeed = 2

    private lateinit var keyboard: Keyboard;

    fun init() {

        keyboard = Keyboard()

    }

    fun loadImageResources() {

    }


    override fun tick(soyokaze: Soyokaze) {
    }

    override fun drawImage(soyokaze: Soyokaze, context: CanvasRenderingContext2D) {
        TODO("Not yet implemented")
    }

    override fun <T> setValue(key: String, value: T) {
        TODO("Not yet implemented")
    }

    override fun getNode(id: String): Node? {
        TODO("Not yet implemented")
    }

    fun update() {

        if (keyboard.up) {
            posY -= moveSpeed
            angle = 3
        }
        if (keyboard.down) {
            posY += moveSpeed
            angle = 0
        }
        if (keyboard.left) {
            posX -= moveSpeed
            angle = 1
        }
        if (keyboard.right) {
            posX += moveSpeed
            angle = 2
        }


        //Animation
        val moveSize = 4 // 次のアニメーションまでに必要なtick数
        if (keyboard.up || keyboard.down || keyboard.left || keyboard.right) {
            modeAnimationCount += 1
            if (modeAnimationCount == 4 * moveSize) {
                modeAnimationCount = 0
            }
        } else {
            //はじめの一歩のアニメーションを確実にするためにアニメーションのしきい値の-1に設定する
            modeAnimationCount = moveSize - 1
            firstStep = true
        }

        modeAnimationPoint = modeAnimationCount / moveSize

        /*
        if (modeAnimationCount == 1 && firstStep) {
            modeAnimationPoint = 1
            firstStep = false
        }*/


    }

    fun drawImage(context: CanvasRenderingContext2D, sprite: Image) {

        context.drawImage(
            sprite,
            16.0 * modeAnimationPoint, 16.0 * (angle + 1), 16.0, 16.0,
            posX, posY, RendererConst.imageScale.toDouble(), RendererConst.imageScale.toDouble()
        )

    }


}