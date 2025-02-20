package ir.rezazarchi.hamrahorder.list.presentation.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import ir.rezazarchi.hamrahorder.R
import ir.rezazarchi.hamrahorder.core.utils.ObserveAsEvents
import ir.rezazarchi.hamrahorder.core.utils.UiText
import ir.rezazarchi.hamrahorder.list.presentation.viewmodel.OrderListEffects
import ir.rezazarchi.hamrahorder.list.presentation.viewmodel.OrderListEvents.GetOrdersList
import ir.rezazarchi.hamrahorder.list.presentation.viewmodel.OrderListState
import ir.rezazarchi.hamrahorder.list.presentation.viewmodel.OrdersListViewModel
import ir.rezazarchi.hamrahorder.ui.theme.HamrahOrderTheme
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun OrdersListScreenRoot(
    modifier: Modifier = Modifier,
    onAddOrderClicked: () -> Unit,
    onOrderFailure: (UiText) -> Unit,
    viewModel: OrdersListViewModel = koinViewModel(),
) {

    val state by viewModel.state.collectAsStateWithLifecycle()

    ObserveAsEvents(viewModel.effect) {
        when (it) {
            is OrderListEffects.OnGetOrdersListFailed -> {
                onOrderFailure(it.errorMessage)
            }
        }
    }

    OrdersListScreen(
        modifier = modifier,
        onAddOrderClicked = onAddOrderClicked,
        onRefresh = { viewModel.onEvent(GetOrdersList) },
        state = state,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun OrdersListScreen(
    modifier: Modifier = Modifier,
    state: OrderListState,
    onAddOrderClicked: () -> Unit,
    onRefresh: () -> Unit,
) {
    val listState = rememberLazyListState()
    val isFabVisible by remember { derivedStateOf { !listState.isScrollInProgress } }
    Box(
        modifier = modifier.fillMaxSize()
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            TopAppBar(title = {
                Text(text = stringResource(R.string.orders_address_list))
            })
            PullToRefreshBox(
                modifier = Modifier.fillMaxSize(),
                isRefreshing = state.isLoading,
                onRefresh = {
                    if (!state.isLoading)
                        onRefresh()
                },
            ) {
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(8.dp),
                    state = listState,
                ) {
                    items(
                        items = state.orders,
                        key = { it.id },
                    ) {
                        ListItem(
                            modifier = Modifier
                                .heightIn(min = 100.dp)
                                .padding(4.dp)
                                .border(
                                    width = 1.dp,
                                    shape = RoundedCornerShape(8.dp),
                                    color = MaterialTheme.colorScheme.outlineVariant,
                                ),
                            headlineContent = {
                                Text(it.address)
                            },
                            supportingContent = {
                                Text(it.fullName)
                            },
                            trailingContent = {
                                Text(it.mobile)
                            }
                        )
                    }
                }
            }
        }
        AnimatedVisibility(
            visible = isFabVisible,
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(16.dp),
            enter = slideInVertically { it },
            exit = slideOutVertically { it },
        ) {
            FloatingActionButton(
                containerColor = MaterialTheme.colorScheme.primary,
                onClick = onAddOrderClicked,
                content = {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = stringResource(R.string.order_screen_info_title)
                    )
                })
        }
    }
}

@Preview
@Composable
private fun PreviewOrdersListScreen() {
    HamrahOrderTheme {
        OrdersListScreen(
            modifier = Modifier,
            onAddOrderClicked = {},
            state = OrderListState(),
            onRefresh = {},
        )
    }
}