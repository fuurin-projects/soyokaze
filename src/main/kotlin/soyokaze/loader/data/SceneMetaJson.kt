package soyokaze.loader.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SceneMetaJson(

    @SerialName("scene_registries")
    val sceneRegistries: Map<String, String> = mutableMapOf()

)
