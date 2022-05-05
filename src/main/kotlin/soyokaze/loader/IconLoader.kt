package soyokaze.loader

import kotlinx.serialization.json.Json
import kotlinx.serialization.json.decodeFromJsonElement
import kotlinx.serialization.json.jsonPrimitive
import soyokaze.loader.data.IconMetaJson
import soyokaze.loader.data.LoaderJson
import soyokaze.platform.browser.FetcherJSFetch
import soyokaze.renderer.Icon
import soyokaze.renderer.SpriteManager

class IconLoader {

    suspend fun load(spriteManager: SpriteManager): Map<String, Icon> {

        val fetcherJSFetch = FetcherJSFetch()

        val loaders: LoaderJson = fetcherJSFetch.fetchLoaderJson("/loader.json")
        val loadersLocation: String? = loaders.iconLoader

        console.log(loadersLocation)

        val icons: IconMetaJson = Json.decodeFromJsonElement(fetcherJSFetch.fetchJson(loadersLocation!!))

        console.log(icons)

        val iconList = mutableMapOf<String, Icon>()

        for (icon in icons.iconRegistries) {
            console.log("ID: ${icon.key}, Locale: ${icon.value}")

            val iconJson = fetcherJSFetch.fetchJson(icon.value)


            val iconData = Icon(
                sprite = spriteManager.spriteList[iconJson["sprite"]!!.jsonPrimitive.content]!!,
                sx = iconJson["sx"]!!.jsonPrimitive.content.toInt(),
                sy = iconJson["sy"]!!.jsonPrimitive.content.toInt(),
                sw = iconJson["sw"]!!.jsonPrimitive.content.toInt(),
                sh = iconJson["sh"]!!.jsonPrimitive.content.toInt()
            )

            iconList[icon.key] = iconData

        }

        console.log("loaded: IconLoader")

        return iconList

    }

}