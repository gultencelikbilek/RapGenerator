import com.google.gson.annotations.SerializedName

data class RapperResponseItem(
    @SerializedName("display_name")
    val displayName: String?,
    @SerializedName("voicemodel_uuid")
    val voicemodelUuid: String?
)