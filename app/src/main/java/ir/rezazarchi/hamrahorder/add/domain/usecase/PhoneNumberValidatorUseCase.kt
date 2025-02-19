package ir.rezazarchi.hamrahorder.add.domain.usecase

import ir.rezazarchi.hamrahorder.add.domain.repo.InputDataValidatorRepo

class PhoneNumberValidatorUseCase(private val repo: InputDataValidatorRepo) {
    operator fun invoke(phone: String): Boolean {
        return repo.isPhoneNumberValid(phone)
    }

}