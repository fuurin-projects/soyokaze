package soyokaze.loader

import kotlinx.serialization.json.jsonPrimitive
import soyokaze.platform.browser.FetcherJSFetch
import soyokaze.scene.World

class WorldLoader {

    suspend fun load() {

        val fetcherJSFetch = FetcherJSFetch()

        val loaders = fetcherJSFetch.fetchJson("/loader.json")
        val loadersLocation = loaders["world_loader"]!!.jsonPrimitive

        console.log(loadersLocation)

        val worlds = fetcherJSFetch.fetchJson(loadersLocation.content)

        console.log(worlds)

        val worldList = mutableMapOf<String, World>()

        for (world in worlds) {
            console.log("ID: ${world.key}, Locale: ${world.value}")

            worldList[world.key] = World(world.key)
        }

    }

}