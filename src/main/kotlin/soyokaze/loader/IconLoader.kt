package soyokaze.loader

import kotlinx.serialization.json.jsonPrimitive
import soyokaze.platform.browser.FetcherJSFetch
import soyokaze.renderer.Icon
import soyokaze.renderer.SpriteManager

class IconLoader {

    suspend fun load(spriteManager: SpriteManager): Map<String, Icon> {

        val fetcherJSFetch = FetcherJSFetch()

        val loaders = fetcherJSFetch.fetchJson("/loader.json")
        val loadersLocation = loaders["icon_loader"]!!.jsonPrimitive

        console.log(loadersLocation)

        val icons = fetcherJSFetch.fetchJson(loadersLocation.content)

        console.log(icons)

        val iconList = mutableMapOf<String, Icon>()

        for (icon in icons) {
            console.log("ID: ${icon.key}, Locale: ${icon.value}")

            val iconJson = fetcherJSFetch.fetchJson(icon.value.jsonPrimitive.content)


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