package ir.rezazarchi.hamrahorder.add.domain.repo

import ir.rezazarchi.hamrahorder.add.domain.model.OrderSubmissionData
import ir.rezazarchi.hamrahorder.core.data.NetworkError
import ir.rezazarchi.hamrahorder.core.data.Result

interface SubmitOrderRepository {
    suspend fun submitOrder(order: OrderSubmissionData): Result<OrderSubmissionData, NetworkError>
}