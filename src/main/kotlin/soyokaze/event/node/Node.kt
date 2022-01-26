package soyokaze.event.node

/**
 * Eventの各種動作の最小単位
 */
interface Node {

    fun getId(): String

    fun getOutput(targetValue: String): Any?

}