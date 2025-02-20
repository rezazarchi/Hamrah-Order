package ir.rezazarchi.hamrahorder.add.data.remote.api

import ir.rezazarchi.hamrahorder.add.data.remote.dto.SubmitOrderRequest
import ir.rezazarchi.hamrahorder.add.data.remote.dto.SubmitOrderResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST

interface SubmitOrderService {

    @Headers("Content-Type: application/json")
    @POST("karfarmas/address")
    suspend fun submitOrder(@Body addressRequest: SubmitOrderRequest): Response<SubmitOrderResponse>
}