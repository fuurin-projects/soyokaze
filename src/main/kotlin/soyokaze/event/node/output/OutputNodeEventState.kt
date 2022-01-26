package soyokaze.event.node.output

import soyokaze.Soyokaze
import soyokaze.event.Event
import soyokaze.event.node.InputTarget

/**
 * 特定の値をEventのStateに設定するNode
 */
class OutputNodeEventState(
    val id: String,
    private val inputSettings: List<InputTarget>,
    private val outputSettings: Map<String, Any>
) : OutputNode {

    override fun getId(): String = id

    override fun tick(soyokaze: Soyokaze, event: Event) {

        //val targetId = inputSettings.get("target_id") as String
        //val targetValue = inputSettings.get("target_value") as String

        var value = 0
        for (inputSetting in inputSettings) {
            value += event.getNode(inputSetting.id)?.getOutput(inputSetting.value).toString().toInt()
        }

        //val newState = event.getNode(targetId)?.getOutput(targetValue)

        val key = outputSettings.get("state_id") as String
        event.setValue(key, value)
        //console.log(targetId + " " + newState)
//        outputSettings.forEach { entry ->
//            event.setValue(entry.key, value)
//
//        }

    }

}