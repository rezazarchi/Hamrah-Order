package ir.rezazarchi.hamrahorder.add.data.validator

import android.util.Patterns
import ir.rezazarchi.hamrahorder.add.domain.repo.InputDataValidatorRepo

class InputDataValidatorImplementation : InputDataValidatorRepo {
    override fun validateStringNotEmpty(s: String): Boolean {
        return s.isNotBlank()
    }

    override fun isPhoneNumberValid(phone: String): Boolean {
        return phone.length == 11
                && phone.startsWith('0')
                && Patterns.PHONE.matcher(phone).matches()
    }

    override fun isEmailValid(email: String): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }
}