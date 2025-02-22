package ir.rezazarchi.hamrahorder.main

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import ir.rezazarchi.hamrahorder.R
import ir.rezazarchi.hamrahorder.add.presentation.ui.OrderRootScreen
import ir.rezazarchi.hamrahorder.list.presentation.ui.OrdersListScreenRoot
import ir.rezazarchi.hamrahorder.main.navigation.MainNavigationRoutes
import ir.rezazarchi.hamrahorder.ui.theme.HamrahOrderTheme
import kotlinx.coroutines.launch
import java.util.Locale

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setLocale("fa")
        setContent {
            HamrahOrderTheme {
                val snackbarHostState = remember { SnackbarHostState() }
                val coroutineScope = rememberCoroutineScope()
                val navController = rememberNavController()
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    snackbarHost = {
                        SnackbarHost(hostState = snackbarHostState)
                    },
                ) { innerPadding ->
                    NavHost(
                        navController = navController,
                        startDestination = MainNavigationRoutes.OrdersList,
                    ) {
                        composable<MainNavigationRoutes.OrdersList> {
                            OrdersListScreenRoot(
                                onAddOrderClicked = {
                                    navController.navigate(MainNavigationRoutes.AddOrder)
                                },
                                onOrderFailure = {
                                    coroutineScope.launch {
                                        showSnackBar(
                                            snackbarHostState,
                                            it.asString(applicationContext),
                                        )
                                    }
                                },
                            )
                        }
                        composable<MainNavigationRoutes.AddOrder> {
                            OrderRootScreen(
                                modifier = Modifier.padding(innerPadding),
                                onSubmittedSuccessfully = {
                                    coroutineScope.launch {
                                        navController.navigateUp()
                                        showSnackBar(
                                            snackbarHostState,
                                            getString(R.string.submit_success)
                                        )
                                    }
                                },
                                onSubmitFailure = {
                                    navController.navigateUp()
                                    coroutineScope.launch {
                                        showSnackBar(
                                            snackbarHostState,
                                            it.asString(applicationContext)
                                        )
                                    }
                                }
                            )
                        }
                    }
                }
            }
        }
    }

    private suspend fun showSnackBar(snackbarHostState: SnackbarHostState, message: String) {
        snackbarHostState.showSnackbar(
            message = message,
            withDismissAction = true,
            duration = SnackbarDuration.Long,
        )
    }

    private fun setLocale(languageCode: String) {
        Build.VERSION_CODES.N
        val locale = Locale(languageCode)
        Locale.setDefault(locale)
        val config = resources.configuration
        config.setLocale(locale)
        createConfigurationContext(config)

    }
}