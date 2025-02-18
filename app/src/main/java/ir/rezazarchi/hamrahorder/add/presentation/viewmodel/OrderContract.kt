package ir.rezazarchi.hamrahorder.add.presentation.viewmodel

import ir.rezazarchi.hamrahorder.add.domain.model.Gender

data class OrderState(
    var name: String,
    var isNameCorrect: Boolean,
    var family: String,
    var isFamilyCorrect: Boolean,
    var phone: String,
    var isPhoneCorrect: Boolean,
    var mobile: String,
    var isMobileCorrect: Boolean,
    var fullAddress: String,
    var isFullAddressCorrect: Boolean,
    var gender: Gender,
    var lat: Double,
    var lng: Double,
)