package ir.rezazarchi.hamrahorder.add.domain.usecase

import ir.rezazarchi.hamrahorder.add.domain.model.OrderSubmissionData
import ir.rezazarchi.hamrahorder.add.domain.repo.SubmitOrderRepository

class SubmitOrderUseCase(private val repo: SubmitOrderRepository) {
    suspend operator fun invoke(order: OrderSubmissionData) = repo.submitOrder(order)
}