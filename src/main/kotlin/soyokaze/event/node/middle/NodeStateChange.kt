package soyokaze.event.node.middle

import soyokaze.Soyokaze
import soyokaze.event.Event

class NodeStateChange : MiddleNode {

    override fun getId(): String = "-"
    override fun getOutput(targetValue: String): Any? {
        TODO("Not yet implemented")
    }

    override fun tick(soyokaze: Soyokaze, event: Event) {

    }


}