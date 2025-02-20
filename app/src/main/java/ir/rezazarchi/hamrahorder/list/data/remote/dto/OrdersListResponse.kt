package ir.rezazarchi.hamrahorder.list.data.remote.dto


import androidx.annotation.Keep
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Keep
@Serializable
data class OrdersListResponse(
    @SerialName("id")
    val id: String,
    @SerialName("address_id")
    val addressId: String?,
    @SerialName("address")
    val address: String,
    @SerialName("last_name")
    val lastName: String,
    @SerialName("first_name")
    val firstName: String,
    @SerialName("gender")
    val gender: String?,
    @SerialName("lat")
    val lat: Double?,
    @SerialName("lng")
    val lng: Double?,
    @SerialName("coordinate_mobile")
    val coordinateMobile: String,
    @SerialName("coordinate_phone_number")
    val coordinatePhoneNumber: String,
)