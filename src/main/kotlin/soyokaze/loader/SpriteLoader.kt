package soyokaze.loader

import kotlinx.serialization.json.Json
import kotlinx.serialization.json.decodeFromJsonElement
import org.w3c.dom.Image
import soyokaze.loader.data.LoaderJson
import soyokaze.loader.data.SpriteMetaJson
import soyokaze.platform.browser.FetcherJSFetch

class SpriteLoader {

    suspend fun load(loadItem: (itemName: String, count: Int, maxCount: Int) -> Unit = { _, _, _ -> }): Map<String, Image> {

        console.log("load")

        val fetcherJSFetch = FetcherJSFetch()

        val loaders: LoaderJson = fetcherJSFetch.fetchLoaderJson("/loader.json")

        val loadersLocation: String? = loaders.spriteLoader

        console.log(loadersLocation)

        val sprites: SpriteMetaJson = Json.decodeFromJsonElement(fetcherJSFetch.fetchJson(loadersLocation!!))

        console.log(sprites)

        loadItem("", 0, sprites.spriteRegistries.size)

        //sleep(2000).await()

        val spriteList = mutableMapOf<String, Image>()

        var count = 0;

        for (sprite in sprites.spriteRegistries) {
            console.log("ID: ${sprite.key}, Locale: ${sprite.value}")

            val spriteImage = Image();
            spriteImage.src = sprite.value
            //sprite.src = "/gamedata/resources/sprite001.png";
            spriteList[sprite.key] = spriteImage

            count++
            loadItem(sprite.key, count, sprites.spriteRegistries.size)

        }

        return spriteList

    }

}

