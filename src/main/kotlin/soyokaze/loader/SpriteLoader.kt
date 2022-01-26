package soyokaze.loader

import kotlinx.serialization.json.jsonPrimitive
import org.w3c.dom.Image
import soyokaze.platform.browser.FetcherJSFetch

class SpriteLoader {

    suspend fun load(loadItem: (itemName: String, count: Int, maxCount: Int) -> Unit = { _, _, _ -> }): Map<String, Image> {

        console.log("load")

        val fetcherJSFetch = FetcherJSFetch()

        val loaders = fetcherJSFetch.fetchJson("/loader.json")
        val loadersLocation = loaders["sprite_loader"]!!.jsonPrimitive

        console.log(loadersLocation.content)

        val sprites = fetcherJSFetch.fetchJson(loadersLocation.content)

        console.log(sprites)

        loadItem("", 0, sprites.size)

        //sleep(2000).await()

        val spriteList = mutableMapOf<String, Image>()

        var count = 0;

        for (sprite in sprites) {
            console.log("ID: ${sprite.key}, Locale: ${sprite.value}")

            val spriteImage = Image();
            spriteImage.src = sprite.value.jsonPrimitive.content
            //sprite.src = "/gamedata/resources/sprite001.png";
            spriteList[sprite.key] = spriteImage

            count++
            loadItem(sprite.key, count, sprites.size)

        }

        return spriteList

    }

}

