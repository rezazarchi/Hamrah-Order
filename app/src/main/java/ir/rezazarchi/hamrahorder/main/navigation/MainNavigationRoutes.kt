package ir.rezazarchi.hamrahorder.main.navigation

import androidx.annotation.Keep
import kotlinx.serialization.Serializable

@Keep
sealed class MainNavigationRoutes {
    @Serializable
    data object OrdersList : MainNavigationRoutes()

    @Serializable
    data object AddOrder : MainNavigationRoutes()

}
