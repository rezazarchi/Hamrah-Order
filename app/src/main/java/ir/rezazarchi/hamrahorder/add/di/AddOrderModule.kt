package ir.rezazarchi.hamrahorder.add.di

import ir.rezazarchi.hamrahorder.add.data.remote.api.SubmitOrderService
import ir.rezazarchi.hamrahorder.add.data.remote.repo.SubmitOrderRepositoryImplementation
import ir.rezazarchi.hamrahorder.add.data.validator.InputDataValidatorImplementation
import ir.rezazarchi.hamrahorder.add.domain.repo.InputDataValidatorRepo
import ir.rezazarchi.hamrahorder.add.domain.repo.SubmitOrderRepository
import ir.rezazarchi.hamrahorder.add.domain.usecase.EmptyTextValidatorUseCase
import ir.rezazarchi.hamrahorder.add.domain.usecase.PhoneNumberValidatorUseCase
import ir.rezazarchi.hamrahorder.add.domain.usecase.SubmitOrderUseCase
import ir.rezazarchi.hamrahorder.add.presentation.viewmodel.OrderViewModel
import ir.rezazarchi.hamrahorder.core.network.RETROFIT
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.core.qualifier.named
import org.koin.dsl.bind
import org.koin.dsl.module
import retrofit2.Retrofit

val addOrderModule = module {
    singleOf(::InputDataValidatorImplementation) bind InputDataValidatorRepo::class
    single { get<Retrofit>(named(RETROFIT)).create(SubmitOrderService::class.java) }
    singleOf(::SubmitOrderRepositoryImplementation) bind SubmitOrderRepository::class
    factoryOf(::EmptyTextValidatorUseCase)
    factoryOf(::PhoneNumberValidatorUseCase)
    factoryOf(::SubmitOrderUseCase)
    viewModelOf(::OrderViewModel)
}