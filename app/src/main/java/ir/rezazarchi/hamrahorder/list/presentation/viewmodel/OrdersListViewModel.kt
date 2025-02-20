package ir.rezazarchi.hamrahorder.list.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ir.rezazarchi.hamrahorder.core.data.onError
import ir.rezazarchi.hamrahorder.core.data.onSuccess
import ir.rezazarchi.hamrahorder.core.utils.toUiText
import ir.rezazarchi.hamrahorder.list.domain.usecase.GetOrdersListUseCase
import kotlinx.collections.immutable.toImmutableList
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class OrdersListViewModel(private val getOrdersList: GetOrdersListUseCase) : ViewModel() {

    private val _state = MutableStateFlow(OrderListState())
    val state = _state.onStart {
        fetchOrdersList()
    }.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5000L),
        _state.value,
    )

    private val _effect = Channel<OrderListEffects>()
    val effect = _effect.receiveAsFlow()

    fun onEvent(event: OrderListEvents) {
        when (event) {
            is OrderListEvents.GetOrdersList ->
                fetchOrdersList()
        }
    }

    private fun fetchOrdersList() {
        _state.update { it.copy(isLoading = true) }
        viewModelScope.launch {
            getOrdersList()
                .onSuccess { list ->
                    _state.update {
                        it.copy(
                            orders = list.toImmutableList(),
                            isLoading = false,
                        )
                    }
                }
                .onError {
                    _state.update { it.copy(isLoading = false) }
                    _effect.send(OrderListEffects.OnGetOrdersListFailed(it.toUiText()))
                }
        }
    }
}