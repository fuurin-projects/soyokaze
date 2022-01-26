package soyokaze.event.node.input

import soyokaze.Soyokaze
import soyokaze.event.Event
import soyokaze.event.node.Node

/**
 * 処理の起点になるNode
 */
interface InputNode : Node {

    fun tick(soyokaze: Soyokaze, event: Event)

    override fun getOutput(targetValue: String): Any

}