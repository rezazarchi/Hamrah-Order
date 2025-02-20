package ir.rezazarchi.hamrahorder.list.di

import ir.rezazarchi.hamrahorder.core.network.RETROFIT
import ir.rezazarchi.hamrahorder.list.data.remote.api.GetOrdersListService
import ir.rezazarchi.hamrahorder.list.data.remote.repo.GetOrdersListRepoImplementation
import ir.rezazarchi.hamrahorder.list.domain.repo.GetOrdersListRepo
import ir.rezazarchi.hamrahorder.list.domain.usecase.GetOrdersListUseCase
import ir.rezazarchi.hamrahorder.list.presentation.viewmodel.OrdersListViewModel
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.core.qualifier.named
import org.koin.dsl.bind
import org.koin.dsl.module
import retrofit2.Retrofit

val orderListModule = module {
    single { get<Retrofit>(named(RETROFIT)).create(GetOrdersListService::class.java) }
    singleOf(::GetOrdersListRepoImplementation) bind GetOrdersListRepo::class
    factoryOf(::GetOrdersListUseCase)
    viewModelOf(::OrdersListViewModel)

}