package ir.rezazarchi.hamrahorder.list.data.remote.mapper

import ir.rezazarchi.hamrahorder.list.data.remote.dto.OrdersListResponse
import ir.rezazarchi.hamrahorder.list.domain.model.Order

fun OrdersListResponse.toOrder(): Order {
    return Order(
        id = id,
        fullName = "$firstName $lastName",
        mobile = coordinateMobile,
        address = address,
    )
}