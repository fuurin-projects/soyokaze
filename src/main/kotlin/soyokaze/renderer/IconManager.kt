package soyokaze.renderer

class IconManager {

    lateinit var iconList: MutableMap<String, Icon>

    fun init() {
        iconList = mutableMapOf()
    }

    fun postInit(iconList: Map<String, Icon>) {
        this.iconList += iconList

    }

}