package soyokaze.loader

import kotlinx.serialization.json.jsonPrimitive
import soyokaze.platform.browser.FetcherJSFetch
import soyokaze.scene.Scene
import soyokaze.scene.SceneWorld

class SceneLoader {

    suspend fun load(): Map<String, Scene> {

        val fetcherJSFetch = FetcherJSFetch()

        val loaders = fetcherJSFetch.fetchJson("/loader.json")
        val loadersLocation = loaders["scene_loader"]!!.jsonPrimitive

        console.log(loadersLocation)

        val scenes = fetcherJSFetch.fetchJson(loadersLocation.content)

        console.log(scenes)

        val sceneList = mutableMapOf<String, Scene>()

        for (scene in scenes) {
            console.log("ID: ${scene.key}, Locale: ${scene.value}")

            val sceneJson = fetcherJSFetch.fetchJson(scene.value.jsonPrimitive.content)

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