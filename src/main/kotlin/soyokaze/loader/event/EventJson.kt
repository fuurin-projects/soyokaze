package soyokaze.loader.event

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Event情報が入っているJson
 */
@Serializable
data class EventJson(

    @SerialName("name")
    val name: String,

    @SerialName("preference")
    val preference: Preference,

    @SerialName("node_list")
    val nodeList: List<EventNode>,

    @SerialName("image_mapping")
    val imageMapping: Map<String, String>,

    @SerialName("state_image")
    val stateImage: Map<String, String>
)

@Serializable
data class Preference(
    @SerialName("angle")
    val angle: Int,
    @SerialName("animation_count")
    val animationCount: Int
)

@Serializable
data class EventNode(

    val id: String,

    val type: String,

    @SerialName("input")
    var input: List<Input> = arrayListOf(),

    @SerialName("when")
    var whenString: Map<String, String> = mutableMapOf(),

    @SerialName("apply")
    var apply: Map<String, String>

)

@Serializable
data class Input(

    @SerialName("target_id")
    val targetID: String,

    @SerialName("target_value")
    val targetValue: String

)

@Serializable
data class Output(

    @SerialName("target_id")
    val targetID: String,

    @SerialName("target_value")
    val targetValue: String

)