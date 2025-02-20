package ir.rezazarchi.hamrahorder.add.domain.model

data class OrderSubmissionData(
    val name: String,
    val family: String,
    val mobile: String,
    val phone: String,
    val address: String,
    val gender: Gender,
    val selectedLocation: SelectedLocation,
)
