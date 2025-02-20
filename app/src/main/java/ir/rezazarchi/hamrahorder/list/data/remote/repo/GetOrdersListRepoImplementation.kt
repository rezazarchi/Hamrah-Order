package ir.rezazarchi.hamrahorder.list.data.remote.repo

import ir.rezazarchi.hamrahorder.core.data.NetworkError
import ir.rezazarchi.hamrahorder.core.data.Result
import ir.rezazarchi.hamrahorder.core.data.map
import ir.rezazarchi.hamrahorder.core.utils.safeCall
import ir.rezazarchi.hamrahorder.list.data.remote.api.GetOrdersListService
import ir.rezazarchi.hamrahorder.list.data.remote.dto.OrdersListResponse
import ir.rezazarchi.hamrahorder.list.data.remote.mapper.toOrder
import ir.rezazarchi.hamrahorder.list.domain.model.Order
import ir.rezazarchi.hamrahorder.list.domain.repo.GetOrdersListRepo

class GetOrdersListRepoImplementation(private val getOrdersListService: GetOrdersListService) :
    GetOrdersListRepo {
    override suspend fun getOrdersList(): Result<List<Order>, NetworkError> {
        return safeCall {
            getOrdersListService.getOrdersList()
        }.map {
            it.map(OrdersListResponse::toOrder)
        }
    }
}