package ir.rezazarchi.hamrahorder.list.data.remote.api

import ir.rezazarchi.hamrahorder.list.data.remote.dto.OrdersListResponse
import retrofit2.Response
import retrofit2.http.GET

interface GetOrdersListService {
    @GET("karfarmas/address")
    suspend fun getOrdersList(): Response<List<OrdersListResponse>>

}