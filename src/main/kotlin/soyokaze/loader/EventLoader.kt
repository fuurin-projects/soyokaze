package soyokaze.loader

import kotlinx.serialization.json.Json
import kotlinx.serialization.json.decodeFromJsonElement
import kotlinx.serialization.json.jsonPrimitive
import soyokaze.event.DynamicEvent
import soyokaze.event.Event
import soyokaze.event.node.InputTarget
import soyokaze.event.node.input.InputNode
import soyokaze.event.node.input.InputNodeController
import soyokaze.event.node.middle.MiddleNode
import soyokaze.event.node.middle.NodeStateChange
import soyokaze.event.node.output.OutputNode
import soyokaze.event.node.output.OutputNodeEventState
import soyokaze.loader.event.EventJson
import soyokaze.platform.browser.FetcherJSFetch


class EventLoader {

    suspend fun load(): Map<String, Event> {

        val fetcherJSFetch = FetcherJSFetch()

        val loaders = fetcherJSFetch.fetchJson("/loader.json")
        val loadersLocation = loaders["event_loader"]!!.jsonPrimitive

        console.log(loadersLocation)

        val events = fetcherJSFetch.fetchJson(loadersLocation.content)

        console.log(events)

        val iconList = mutableMapOf<String, Event>()

        val jsonFormatter = Json {
            ignoreUnknownKeys = true
        }

        for (event in events) {
            console.log("ID: ${event.key}, Locale: ${event.value}")

            val eventJsonElement = fetcherJSFetch.fetchJson(event.value.jsonPrimitive.content)

            console.log(eventJsonElement)

            val eventJson = jsonFormatter.decodeFromJsonElement<EventJson>(eventJsonElement)

            //Input
            val inputNode = mutableListOf<InputNode>()
            inputNode += eventJson.nodeList.filter { eventNode ->
                eventNode.type.startsWith("input.")
            }.map { eventNode ->

                if (eventNode.type == "input.controller") {
                    InputNodeController(
                        id = eventNode.id,
                        type = eventNode.whenString["type"]!!,
                        outputSettings = eventNode.apply
                    )
                } else {
                    throw IllegalStateException("想定外のNodeタイプです. type=${eventNode.type}")
                }

            }.toList()

            //Middle
            val middleNode = mutableListOf<MiddleNode>()
            middleNode += eventJson.nodeList.filter { eventNode ->
                eventNode.type.startsWith("middle.")
            }.map { eventNode ->

                if (eventNode.type == "middle.state_change") {
                    NodeStateChange()
                } else {
                    throw IllegalStateException("想定外のNodeタイプです. type=${eventNode.type}")
                }

            }.toList()

            //Output
            val outputNode = mutableListOf<OutputNode>()
            outputNode += eventJson.nodeList.filter { eventNode ->
                eventNode.type.startsWith("output.")
            }.map { eventNode ->

                if (eventNode.type == "output.event_state") {
                    OutputNodeEventState(
                        id = eventNode.id,
                        inputSettings = eventNode.input.map { input ->
                            InputTarget(input.targetID, input.targetValue)
                        },
                        outputSettings = eventNode.apply
                    )
                } else {
                    throw IllegalStateException("想定外のNodeタイプです. type=${eventNode.type}")
                }

            }.toList()


            iconList[event.key] =
                DynamicEvent(
                    inputNodeList = inputNode,
                    middleNodeList = middleNode,
                    outputNodeList = outputNode,
                    imageMapping = eventJson.imageMapping
                )

        }

        console.log("loaded: EventLoader")

        return iconList

    }

}