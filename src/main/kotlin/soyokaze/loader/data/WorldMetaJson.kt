package soyokaze.loader.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class WorldMetaJson(

    @SerialName("world_registries")
    val worldRegistries: Map<String, String> = mutableMapOf()

)
