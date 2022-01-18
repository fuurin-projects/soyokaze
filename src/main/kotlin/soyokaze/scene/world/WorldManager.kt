package soyokaze.scene.world

import soyokaze.scene.World

class WorldManager {

    val worldList = mutableMapOf<String, World>()

    fun postInit(worldList: Map<String, World>) {
        this.worldList += worldList
    }

}