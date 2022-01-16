package soyokaze.loader

import kotlinx.serialization.json.jsonPrimitive
import soyokaze.platform.browser.FetcherJSFetch

class SpriteLoader {

    suspend fun load() {

        console.log("load")

        val fetcherJSFetch = FetcherJSFetch()

        val loaders = fetcherJSFetch.fetchJson("/loader.json")
        val loadersLocation = loaders["sprite_loader"]!!.jsonPrimitive

        console.log(loadersLocation.content)

        val sprites = fetcherJSFetch.fetchJson(loadersLocation.content)

        console.log(sprites)

        for (sprite in sprites) {
            console.log("ID: ${sprite.key}, Locale: ${sprite.value}")
        }

    }

}

