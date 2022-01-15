package soyokaze.loader

import kotlinx.browser.window
import soyokaze.await
import soyokaze.keys
import kotlin.js.Json

class SpriteLoader {

    suspend fun load() {

        console.log("load")

        val loaders = window.fetch("/loader.json").then { it.json() }.then { res -> res }.await() as Json
        val loadersLocation = loaders["sprite_loader"]

        console.log(loadersLocation)
        
        val sprites: dynamic = window.fetch(loadersLocation).then { it.json() }.then { res -> res }.await()

        console.log(sprites)

        for (key in keys(sprites)) {
            val spriteLocale = (sprites as Json).get(key)
            console.log("ID: ${key}, Locale: ${spriteLocale}")
        }

    }

}

