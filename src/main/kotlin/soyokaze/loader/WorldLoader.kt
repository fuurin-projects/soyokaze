package soyokaze.loader

import kotlinx.serialization.json.Json
import kotlinx.serialization.json.decodeFromJsonElement
import kotlinx.serialization.json.jsonPrimitive
import soyokaze.loader.data.LoaderJson
import soyokaze.platform.browser.FetcherJSFetch
import soyokaze.scene.World

class WorldLoader {

    suspend fun load(): Map<String, World> {

        console.log("load: WorldLoader")

        val fetcherJSFetch = FetcherJSFetch()

        val loaders: LoaderJson = fetcherJSFetch.fetchLoaderJson("/loader.json")
        val loadersLocation: String? = loaders.worldLoader

        console.log(loadersLocation)

        val worlds = fetcherJSFetch.fetchJson(loadersLocation!!)

        console.log(worlds)

        val worldList = mutableMapOf<String, World>()

        for (world in worlds) {
            console.log("ID: ${world.key}, Locale: ${world.value}")

            val worldJson = fetcherJSFetch.fetchJson(world.value.jsonPrimitive.content)

            worldList[world.key] = World(
                name = worldJson["world_name"]!!.jsonPrimitive.content,
                imageData = Json.decodeFromJsonElement<Array<Array<Int>>>(worldJson["image_data"]!!),
                imageMapping = Json.decodeFromJsonElement<Map<Int, String>>(worldJson["image_mapping"]!!),
            )
        }

        console.log("loaded: WorldLoader")

        return worldList

    }

}