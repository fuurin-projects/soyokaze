package soyokaze.event.node.output

import soyokaze.Soyokaze
import soyokaze.event.Event
import soyokaze.event.node.Node

interface OutputNode : Node {

    fun tick(soyokaze: Soyokaze, event: Event)

    override fun getOutput(targetValue: String) = null

}