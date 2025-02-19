package ir.rezazarchi.hamrahorder.add.presentation.ui.navigation

import androidx.annotation.Keep
import kotlinx.serialization.Serializable

@Keep
sealed class OrderNavigationRoutes {
    @Serializable
    data object OrderInfo : OrderNavigationRoutes()

    @Serializable
    data object OrderMap : OrderNavigationRoutes()

}