package soyokaze.loader.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class IconMetaJson(
    @SerialName("icon_registries")
    val iconRegistries: Map<String, String> = mutableMapOf()
)