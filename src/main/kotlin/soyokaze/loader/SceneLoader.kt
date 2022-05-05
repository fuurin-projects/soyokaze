package soyokaze.loader

import kotlinx.serialization.json.Json
import kotlinx.serialization.json.decodeFromJsonElement
import kotlinx.serialization.json.jsonPrimitive
import soyokaze.loader.data.LoaderJson
import soyokaze.loader.data.SceneMetaJson
import soyokaze.platform.browser.FetcherJSFetch
import soyokaze.scene.Scene
import soyokaze.scene.SceneWorld

class SceneLoader {

    suspend fun load(): Map<String, Scene> {

        val fetcherJSFetch = FetcherJSFetch()

        val loaders: LoaderJson = fetcherJSFetch.fetchLoaderJson("/loader.json")
        val loadersLocation: String? = loaders.sceneLoader

        console.log(loadersLocation)

        val scenes: SceneMetaJson = Json.decodeFromJsonElement(fetcherJSFetch.fetchJson(loadersLocation!!))

        console.log(scenes)

        val sceneList = mutableMapOf<String, Scene>()

        for (scene in scenes.sceneRegistries) {
            console.log("ID: ${scene.key}, Locale: ${scene.value}")

            val sceneJson = fetcherJSFetch.fetchJson(scene.value)

            if (sceneJson["type"]!!.jsonPrimitive.content == "world") {
                sceneList[sceneJson["name"]!!.jsonPrimitive.content] =
                    SceneWorld(
                        name = sceneJson["name"]!!.jsonPrimitive.content,
                        data = sceneJson["data"]!!.jsonPrimitive.content
                    );
            }


        }

        console.log("loaded scene: size=${sceneList.size}")

        return sceneList

    }

}