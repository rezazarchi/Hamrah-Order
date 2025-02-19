package ir.rezazarchi.hamrahorder.add.presentation.viewmodel

import androidx.lifecycle.ViewModel
import ir.rezazarchi.hamrahorder.add.domain.usecase.EmptyTextValidatorUseCase
import ir.rezazarchi.hamrahorder.add.domain.usecase.PhoneNumberValidatorUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class OrderViewModel(
    private val isTextValid: EmptyTextValidatorUseCase,
    private val isPhoneNumberValid: PhoneNumberValidatorUseCase,
) : ViewModel() {

    private val _state = MutableStateFlow(OrderState())
    val state = _state.asStateFlow()

    fun onEvent(event: OrderEvents) {
        when (event) {
            is OrderEvents.OnAddressChanged -> {
                _state.update {
                    it.copy(
                        fullAddress = event.address,
                        isFullAddressCorrect = isTextValid(event.address)
                    )
                }
            }

            is OrderEvents.OnFamilyChanged -> {
                _state.update {
                    it.copy(
                        family = event.family,
                        isFamilyCorrect = isTextValid(event.family)
                    )
                }
            }

            is OrderEvents.OnGenderSelected -> {
                _state.update {
                    it.copy(
                        gender = event.gender
                    )
                }
            }

            is OrderEvents.OnMobileChanged -> {
                _state.update {
                    it.copy(
                        mobile = event.mobile,
                        isMobileCorrect = isPhoneNumberValid(event.mobile)
                    )
                }
            }

            is OrderEvents.OnNameChanged -> {
                _state.update {
                    it.copy(
                        name = event.name,
                        isNameCorrect = isTextValid(event.name)
                    )
                }
            }

            is OrderEvents.OnPhoneChanged -> {
                _state.update {
                    it.copy(
                        phone = event.phone,
                        isPhoneCorrect = isPhoneNumberValid(event.phone)
                    )
                }
            }

            is OrderEvents.OnLocationSelected -> {
                _state.update {
                    it.copy(
                        location = event.selectedLocation
                    )
                }
            }

            OrderEvents.OnSubmitOrder -> {
                TODO()
            }
        }
    }
}