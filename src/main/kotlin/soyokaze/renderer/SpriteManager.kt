package soyokaze.renderer

import org.w3c.dom.Image

class SpriteManager {

    private lateinit var spriteList: MutableMap<String, Image>

    fun init() {

        val sprite = Image();
        sprite.src = "/gamedata/resources/sprite001.png";

        spriteList.put("sprite001", sprite)

    }

}