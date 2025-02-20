package ir.rezazarchi.hamrahorder.list.domain.usecase

import ir.rezazarchi.hamrahorder.core.data.NetworkError
import ir.rezazarchi.hamrahorder.core.data.Result
import ir.rezazarchi.hamrahorder.list.domain.model.Order
import ir.rezazarchi.hamrahorder.list.domain.repo.GetOrdersListRepo

class GetOrdersListUseCase(private val getOrdersListRepo: GetOrdersListRepo) {
    suspend operator fun invoke(): Result<List<Order>, NetworkError> {
        return getOrdersListRepo.getOrdersList()
    }

}