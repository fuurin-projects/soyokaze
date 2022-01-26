package soyokaze.scene

import org.w3c.dom.CanvasRenderingContext2D
import soyokaze.Soyokaze

interface Scene {

    fun start() {

    }

    /**
     * 画面が表示されているとき呼ばれる
     */
    fun tick(soyokaze: Soyokaze) {

    }

    fun draw(soyokaze: Soyokaze, context: CanvasRenderingContext2D) {

    }


}