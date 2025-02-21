package ir.rezazarchi.hamrahorder.add.domain.usecase

import ir.rezazarchi.hamrahorder.add.data.validator.InputDataValidatorImplementation
import ir.rezazarchi.hamrahorder.add.domain.repo.InputDataValidatorRepo
import org.junit.Assert.*

import org.junit.Before
import org.junit.Test

class EmptyTextValidatorUseCaseTest {

    private lateinit var emptyTextValidatorUseCase: EmptyTextValidatorUseCase
    private lateinit var validator: InputDataValidatorRepo

    @Before
    fun setUp() {
        validator = InputDataValidatorImplementation()
        emptyTextValidatorUseCase = EmptyTextValidatorUseCase(validator)
    }

    @Test
    fun `blank text returns false`() {
        val result = emptyTextValidatorUseCase(" ")
        assertFalse(result)
    }

    @Test
    fun `empty text returns false`() {
        val result = emptyTextValidatorUseCase("")
        assertFalse(result)
    }

    @Test
    fun `non-empty text returns true`() {
        val result = emptyTextValidatorUseCase("test")
        assertTrue(result)
    }

}