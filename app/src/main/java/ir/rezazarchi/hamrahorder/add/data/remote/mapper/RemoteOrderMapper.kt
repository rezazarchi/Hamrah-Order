package ir.rezazarchi.hamrahorder.add.data.remote.mapper

import ir.rezazarchi.hamrahorder.add.data.remote.dto.SubmitOrderRequest
import ir.rezazarchi.hamrahorder.add.data.remote.dto.SubmitOrderResponse
import ir.rezazarchi.hamrahorder.add.domain.model.Gender
import ir.rezazarchi.hamrahorder.add.domain.model.OrderSubmissionData
import ir.rezazarchi.hamrahorder.add.domain.model.SelectedLocation

fun OrderSubmissionData.toSubmitOrderRequest(): SubmitOrderRequest {
    return SubmitOrderRequest(
        firstName = name,
        lastName = family,
        address = address,
        lat = selectedLocation.lat,
        lng = selectedLocation.lng,
        coordinateMobile = mobile,
        coordinatePhoneNumber = phone,
        gender = gender.name,
        region = 1,
    )
}

fun SubmitOrderResponse.toOrder(): OrderSubmissionData {
    return OrderSubmissionData(
        name = firstName,
        family = lastName,
        mobile = coordinateMobile,
        phone = coordinatePhoneNumber,
        address = address,
        gender = getGenderFromText(),
        selectedLocation = SelectedLocation(lat, lng)

    )
}

private fun SubmitOrderResponse.getGenderFromText() =
    if (gender.equals("male", false))
        Gender.Male
    else if (gender.equals("female", false))
        Gender.Female
    else
        Gender.Male