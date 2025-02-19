package ir.rezazarchi.hamrahorder.add.presentation.ui

import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import ir.rezazarchi.hamrahorder.R
import ir.rezazarchi.hamrahorder.add.presentation.ui.navigation.OrderNavigationRoutes
import ir.rezazarchi.hamrahorder.add.presentation.viewmodel.OrderEvents.OnAddressChanged
import ir.rezazarchi.hamrahorder.add.presentation.viewmodel.OrderEvents.OnFamilyChanged
import ir.rezazarchi.hamrahorder.add.presentation.viewmodel.OrderEvents.OnGenderSelected
import ir.rezazarchi.hamrahorder.add.presentation.viewmodel.OrderEvents.OnLocationSelected
import ir.rezazarchi.hamrahorder.add.presentation.viewmodel.OrderEvents.OnMobileChanged
import ir.rezazarchi.hamrahorder.add.presentation.viewmodel.OrderEvents.OnNameChanged
import ir.rezazarchi.hamrahorder.add.presentation.viewmodel.OrderEvents.OnPhoneChanged
import ir.rezazarchi.hamrahorder.add.presentation.viewmodel.OrderEvents.OnSubmitOrder
import ir.rezazarchi.hamrahorder.add.presentation.viewmodel.OrderViewModel
import org.koin.compose.viewmodel.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OrderRootScreen(modifier: Modifier = Modifier) {

    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        when (currentDestination?.route) {
                            OrderNavigationRoutes.OrderInfo.javaClass.canonicalName -> stringResource(
                                R.string.order_screen_info_title
                            )

                            OrderNavigationRoutes.OrderMap.javaClass.canonicalName -> stringResource(
                                R.string.order_screen_map_title
                            )

                            else -> ""
                        }
                    )
                },
                navigationIcon = {
                    navController.previousBackStackEntry?.let {
                        IconButton(
                            onClick = {
                                navController.navigateUp()
                            },
                        ) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Default.ArrowBack,
                                contentDescription = stringResource(R.string.back),
                            )
                        }
                    }
                }
            )
        },
        content = { innerPadding ->

            val viewModel = koinViewModel<OrderViewModel>()

            NavHost(
                navController = navController,
                startDestination = OrderNavigationRoutes.OrderInfo,
                enterTransition = { slideInHorizontally() },
                exitTransition = { slideOutHorizontally() },
            ) {
                composable<OrderNavigationRoutes.OrderInfo> {
                    val state by viewModel.state.collectAsStateWithLifecycle()
                    OrderInfoScreen(
                        orderState = state,
                        modifier = Modifier.padding(innerPadding),
                        onNameValueChanged = { viewModel.onEvent(OnNameChanged(it)) },
                        onFamilyValueChanged = { viewModel.onEvent(OnFamilyChanged(it)) },
                        onMobileValueChanged = { viewModel.onEvent(OnMobileChanged(it)) },
                        onPhoneValueChanged = { viewModel.onEvent(OnPhoneChanged(it)) },
                        onAddressValueChanged = { viewModel.onEvent(OnAddressChanged(it)) },
                        onGenderValueChanged = { viewModel.onEvent(OnGenderSelected(it)) },
                        onNextClicked = {
                            navController.navigate(OrderNavigationRoutes.OrderMap)
                        },
                    )
                }
                composable<OrderNavigationRoutes.OrderMap> {
                    OrderMapScreen(
                        modifier = Modifier.padding(innerPadding),
                        onLocationSelected = { viewModel.onEvent(OnLocationSelected(it)) },
                        onNextClicked = { viewModel.onEvent(OnSubmitOrder) }
                    )
                }
            }
        }
    )
}