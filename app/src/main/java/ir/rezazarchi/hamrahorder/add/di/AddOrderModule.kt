package ir.rezazarchi.hamrahorder.add.di

import ir.rezazarchi.hamrahorder.add.data.validator.InputDataValidatorImplementation
import ir.rezazarchi.hamrahorder.add.domain.repo.InputDataValidatorRepo
import ir.rezazarchi.hamrahorder.add.domain.usecase.EmptyTextValidatorUseCase
import ir.rezazarchi.hamrahorder.add.domain.usecase.PhoneNumberValidatorUseCase
import ir.rezazarchi.hamrahorder.add.presentation.viewmodel.OrderViewModel
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.bind
import org.koin.dsl.module

val addOrderModule = module {
    singleOf(::InputDataValidatorImplementation) bind InputDataValidatorRepo::class
    factoryOf(::EmptyTextValidatorUseCase)
    factoryOf(::PhoneNumberValidatorUseCase)
    viewModelOf(::OrderViewModel)
}