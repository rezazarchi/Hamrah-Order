package ir.rezazarchi.hamrahorder.add.domain.repo

interface InputDataValidatorRepo {
    fun validateStringNotEmpty(s: String): Boolean
    fun isPhoneNumberValid(phone: String): Boolean
    fun isEmailValid(email: String): Boolean
}