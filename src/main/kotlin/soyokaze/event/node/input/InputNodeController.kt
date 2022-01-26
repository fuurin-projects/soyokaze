package soyokaze.event.node.input

import soyokaze.Soyokaze
import soyokaze.event.Event

/**
 * コントローラーの入力を受け付けるNode
 */
class InputNodeController(
    val id: String,
    private val type: String,
    private val outputSettings: Map<String, Any>
) : InputNode {

    private val output: MutableMap<String, Any> = mutableMapOf()

    override fun getId(): String = id

    override fun tick(soyokaze: Soyokaze, event: Event) {

        output.clear()

        //入力リセット
        /*
        if (type == "up_down_released" && soyokaze.keyboard.up.not() && soyokaze.keyboard.down.not()) {
            outputSettings.forEach { entry ->
                output[entry.key] = entry.value
            }
        }
        if (type == "left_right_released" && soyokaze.keyboard.left.not() && soyokaze.keyboard.right.not()) {
            outputSettings.forEach { entry ->
                output[entry.key] = entry.value
            }
        }*/

        //押された向き
        if (type == "up" && soyokaze.keyboard.up) {
            outputSettings.forEach { entry ->
                output[entry.key] = entry.value
            }
        }
        if (type == "down" && soyokaze.keyboard.down) {
            outputSettings.forEach { entry ->
                output[entry.key] = entry.value
            }
        }
        if (type == "left" && soyokaze.keyboard.left) {
            outputSettings.forEach { entry ->
                output[entry.key] = entry.value
            }
        }
        if (type == "right" && soyokaze.keyboard.right) {
            outputSettings.forEach { entry ->
                output[entry.key] = entry.value
            }
        }


    }

    override fun getOutput(targetValue: String): Any {

        return if (output.containsKey(targetValue)) {
            output[targetValue]!!
        } else {
            0;
        }

    }


}