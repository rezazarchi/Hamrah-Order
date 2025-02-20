package ir.rezazarchi.hamrahorder.list.domain.repo

import ir.rezazarchi.hamrahorder.core.data.NetworkError
import ir.rezazarchi.hamrahorder.core.data.Result
import ir.rezazarchi.hamrahorder.list.domain.model.Order

interface GetOrdersListRepo {
    suspend fun getOrdersList(): Result<List<Order>, NetworkError>
}