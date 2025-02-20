package ir.rezazarchi.hamrahorder.add.presentation.viewmodel

import ir.rezazarchi.hamrahorder.add.domain.model.Gender
import ir.rezazarchi.hamrahorder.add.domain.model.SelectedLocation
import ir.rezazarchi.hamrahorder.core.utils.UiText

data class OrderState(
    var name: String = "",
    var isNameCorrect: Boolean = false,
    var family: String = "",
    var isFamilyCorrect: Boolean = false,
    var phone: String = "",
    var isPhoneCorrect: Boolean = false,
    var mobile: String = "",
    var isMobileCorrect: Boolean = false,
    var fullAddress: String = "",
    var isFullAddressCorrect: Boolean = false,
    var gender: Gender = Gender.Male,
    val location: SelectedLocation = SelectedLocation(0.0, 0.0),
)

sealed class OrderEvents {
    data class OnNameChanged(val name: String) : OrderEvents()
    data class OnFamilyChanged(val family: String) : OrderEvents()
    data class OnPhoneChanged(val phone: String) : OrderEvents()
    data class OnMobileChanged(val mobile: String) : OrderEvents()
    data class OnAddressChanged(val address: String) : OrderEvents()
    data class OnGenderSelected(val gender: Gender) : OrderEvents()
    data class OnLocationSelected(val selectedLocation: SelectedLocation) : OrderEvents()
    data object OnSubmitOrder : OrderEvents()
}

sealed class OrderEffects {
    data object OnSubmissionInProgress : OrderEffects()
    data class OnSubmissionFailed(val errorMessage: UiText) : OrderEffects()
    data object OnSubmittedSuccessfully : OrderEffects()
}