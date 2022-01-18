package soyokaze.renderer

import org.w3c.dom.Image

class SpriteManager {

    lateinit var spriteList: MutableMap<String, Image>

    fun init() {
        spriteList = mutableMapOf()
    }

    fun postInit(spriteList: Map<String, Image>) {

//        val sprite = Image();
//        sprite.src = "/gamedata/resources/sprite001.png";
//
//        spriteList.put("sprite001", sprite)

        this.spriteList += spriteList

    }

}