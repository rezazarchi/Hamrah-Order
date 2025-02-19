package ir.rezazarchi.hamrahorder.add.domain.usecase

import ir.rezazarchi.hamrahorder.add.domain.repo.InputDataValidatorRepo

class EmailValidatorUseCase(private val repo: InputDataValidatorRepo) {
    operator fun invoke(email: String): Boolean {
        return repo.isEmailValid(email)
    }

}