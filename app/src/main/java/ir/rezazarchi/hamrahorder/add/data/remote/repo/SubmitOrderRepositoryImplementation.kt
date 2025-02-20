package ir.rezazarchi.hamrahorder.add.data.remote.repo

import ir.rezazarchi.hamrahorder.add.data.remote.api.SubmitOrderService
import ir.rezazarchi.hamrahorder.add.data.remote.mapper.toOrder
import ir.rezazarchi.hamrahorder.add.data.remote.mapper.toSubmitOrderRequest
import ir.rezazarchi.hamrahorder.add.domain.model.OrderSubmissionData
import ir.rezazarchi.hamrahorder.add.domain.repo.SubmitOrderRepository
import ir.rezazarchi.hamrahorder.core.data.NetworkError
import ir.rezazarchi.hamrahorder.core.data.Result
import ir.rezazarchi.hamrahorder.core.data.map
import ir.rezazarchi.hamrahorder.core.utils.safeCall

class SubmitOrderRepositoryImplementation(private val submitOrderService: SubmitOrderService) :
    SubmitOrderRepository {
    override suspend fun submitOrder(order: OrderSubmissionData): Result<OrderSubmissionData, NetworkError> {
        return safeCall {
            submitOrderService.submitOrder(order.toSubmitOrderRequest())
        }.map {
            it.toOrder()
        }
    }
}