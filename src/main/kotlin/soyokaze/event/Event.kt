package soyokaze.event

import org.w3c.dom.CanvasRenderingContext2D
import soyokaze.Soyokaze
import soyokaze.event.node.Node

interface Event {

    fun tick(soyokaze: Soyokaze)

    fun drawImage(soyokaze: Soyokaze, context: CanvasRenderingContext2D)

    fun <T> setValue(key: String, value: T)

    fun getNode(id: String): Node?

}