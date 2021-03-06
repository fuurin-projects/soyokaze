package soyokaze.loader.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SpriteMetaJson(

    @SerialName("sprite_registries")
    val spriteRegistries: Map<String, String> = mutableMapOf()

)