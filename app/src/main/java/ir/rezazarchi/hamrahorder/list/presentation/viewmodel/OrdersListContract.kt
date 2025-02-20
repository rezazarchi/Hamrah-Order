package ir.rezazarchi.hamrahorder.list.presentation.viewmodel

import ir.rezazarchi.hamrahorder.core.utils.UiText
import ir.rezazarchi.hamrahorder.list.domain.model.Order
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf


data class OrderListState(
    val orders: ImmutableList<Order> = persistentListOf(),
    val isLoading: Boolean = false,
)

sealed class OrderListEvents {
    data object GetOrdersList : OrderListEvents()
}

sealed class OrderListEffects {
    data class OnGetOrdersListFailed(val errorMessage: UiText) : OrderListEffects()
}