package ir.rezazarchi.hamrahorder.add.data.remote.dto


import androidx.annotation.Keep
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Keep
@Serializable
data class SubmitOrderRequest(
    @SerialName("region")
    val region: Int?,
    @SerialName("address")
    val address: String?,
    @SerialName("lat")
    val lat: Double?,
    @SerialName("lng")
    val lng: Double?,
    @SerialName("coordinate_mobile")
    val coordinateMobile: String?,
    @SerialName("coordinate_phone_number")
    val coordinatePhoneNumber: String?,
    @SerialName("first_name")
    val firstName: String?,
    @SerialName("last_name")
    val lastName: String?,
    @SerialName("gender")
    val gender: String?,
)