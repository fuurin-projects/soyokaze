package soyokaze.loader.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LoaderJson(

    @SerialName("sprite_loader")
    val spriteLoader: String?,

    @SerialName("world_loader")
    val worldLoader: String?,

    @SerialName("scene_loader")
    val sceneLoader: String?,

    @SerialName("icon_loader")
    val iconLoader: String?,

    @SerialName("event_loader")
    val eventLoader: String?


)
