package ir.rezazarchi.hamrahorder.add.domain.usecase

import ir.rezazarchi.hamrahorder.add.domain.repo.InputDataValidatorRepo

class EmptyTextValidatorUseCase(private val repo: InputDataValidatorRepo) {
    operator fun invoke(text: String): Boolean {
        return repo.validateStringNotEmpty(text)
    }
}