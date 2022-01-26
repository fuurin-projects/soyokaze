package soyokaze.event.node.middle

import soyokaze.Soyokaze
import soyokaze.event.Event
import soyokaze.event.node.Node

interface MiddleNode : Node {

    fun tick(soyokaze: Soyokaze, event: Event)

}