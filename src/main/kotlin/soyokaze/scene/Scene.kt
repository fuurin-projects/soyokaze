package soyokaze.scene

import org.w3c.dom.CanvasRenderingContext2D

interface Scene {

    fun start() {

    }

    /**
     * 画面が表示されているとき呼ばれる
     */
    fun tick() {

    }

    fun draw(context: CanvasRenderingContext2D) {

    }


}