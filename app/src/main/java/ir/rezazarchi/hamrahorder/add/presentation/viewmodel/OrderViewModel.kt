package ir.rezazarchi.hamrahorder.add.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ir.rezazarchi.hamrahorder.add.domain.model.OrderSubmissionData
import ir.rezazarchi.hamrahorder.add.domain.usecase.EmptyTextValidatorUseCase
import ir.rezazarchi.hamrahorder.add.domain.usecase.PhoneNumberValidatorUseCase
import ir.rezazarchi.hamrahorder.add.domain.usecase.SubmitOrderUseCase
import ir.rezazarchi.hamrahorder.core.data.onError
import ir.rezazarchi.hamrahorder.core.data.onSuccess
import ir.rezazarchi.hamrahorder.core.utils.toUiText
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class OrderViewModel(
    private val isTextValid: EmptyTextValidatorUseCase,
    private val isPhoneNumberValid: PhoneNumberValidatorUseCase,
    private val submitOrder: SubmitOrderUseCase,
) : ViewModel() {

    private val _state = MutableStateFlow(OrderState())
    val state = _state.asStateFlow()

    private val _effect = Channel<OrderEffects>()
    val effect = _effect.receiveAsFlow()

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
                submitOrderToServer()
            }
        }
    }

    private fun submitOrderToServer() {
        with(_state.value) {
            viewModelScope.launch {
                _effect.send(OrderEffects.OnSubmissionInProgress)
                submitOrder(
                    OrderSubmissionData(
                        name = name,
                        family = family,
                        mobile = mobile,
                        phone = phone,
                        address = fullAddress,
                        gender = gender,
                        selectedLocation = location
                    )
                ).onSuccess {
                    _effect.send(OrderEffects.OnSubmittedSuccessfully)
                }.onError {
                    _effect.send(OrderEffects.OnSubmissionFailed(it.toUiText()))
                }
            }
        }
    }
}