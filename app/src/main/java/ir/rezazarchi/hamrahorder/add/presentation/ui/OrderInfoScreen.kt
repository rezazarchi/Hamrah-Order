package ir.rezazarchi.hamrahorder.add.presentation.ui

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SegmentedButton
import androidx.compose.material3.SegmentedButtonDefaults
import androidx.compose.material3.SingleChoiceSegmentedButtonRow
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ir.rezazarchi.hamrahorder.R
import ir.rezazarchi.hamrahorder.add.domain.model.Gender
import ir.rezazarchi.hamrahorder.add.presentation.viewmodel.OrderState

@Composable
fun OrderInfoScreen(
    modifier: Modifier = Modifier,
    orderState: OrderState,
    onNameValueChanged: (String) -> Unit,
    onFamilyValueChanged: (String) -> Unit,
    onMobileValueChanged: (String) -> Unit,
    onPhoneValueChanged: (String) -> Unit,
    onAddressValueChanged: (String) -> Unit,
    onGenderValueChanged: (Gender) -> Unit,
    onNextClicked: () -> Unit,
) {
    Column(
        modifier = modifier
            .padding(8.dp)
            .fillMaxSize(),
    ) {
        Text(
            text = stringResource(R.string.enter_your_information_please),
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.secondaryContainer)
                .padding(horizontal = 8.dp, vertical = 16.dp),
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.onSecondaryContainer
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
                .weight(1f)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            TextField(
                value = orderState.name,
                onValueChange = {
                    onNameValueChanged(it)
                },
                modifier = Modifier.fillMaxWidth(),
                leadingIcon = {
                    Text(text = stringResource(R.string.name))
                },
                trailingIcon = {
                    Icon(
                        painter =
                        if (orderState.isNameCorrect)
                            painterResource(R.drawable.check_circle)
                        else
                            painterResource(R.drawable.circle),
                        contentDescription = null,
                        tint = if (orderState.isNameCorrect)
                            Color.Green
                        else
                            MaterialTheme.colorScheme.outlineVariant,
                    )
                }
            )
            TextField(
                value = orderState.family,
                onValueChange = {
                    onFamilyValueChanged(it)
                },
                modifier = Modifier.fillMaxWidth(),
                leadingIcon = {
                    Text(text = stringResource(R.string.family))
                },
                trailingIcon = {
                    Icon(
                        painter =
                        if (orderState.isFamilyCorrect)
                            painterResource(R.drawable.check_circle)
                        else
                            painterResource(R.drawable.circle),
                        contentDescription = null,
                        tint = if (orderState.isFamilyCorrect)
                            Color.Green
                        else
                            MaterialTheme.colorScheme.outlineVariant,
                    )
                }
            )
            TextField(
                value = orderState.mobile,
                onValueChange = {
                    onMobileValueChanged(it)
                },
                modifier = Modifier.fillMaxWidth(),
                leadingIcon = {
                    Text(text = stringResource(R.string.mobile))
                },
                trailingIcon = {
                    Icon(
                        painter =
                        if (orderState.isMobileCorrect)
                            painterResource(R.drawable.check_circle)
                        else
                            painterResource(R.drawable.circle),
                        contentDescription = null,
                        tint = if (orderState.isMobileCorrect)
                            Color.Green
                        else
                            MaterialTheme.colorScheme.outlineVariant,
                    )
                }
            )
            TextField(
                value = orderState.phone,
                onValueChange = {
                    onPhoneValueChanged(it)
                },
                modifier = Modifier.fillMaxWidth(),
                leadingIcon = {
                    Text(text = stringResource(R.string.phone))
                },
                trailingIcon = {
                    Icon(
                        painter =
                        if (orderState.isPhoneCorrect)
                            painterResource(R.drawable.check_circle)
                        else
                            painterResource(R.drawable.circle),
                        contentDescription = null,
                        tint = if (orderState.isPhoneCorrect)
                            Color.Green
                        else
                            MaterialTheme.colorScheme.outlineVariant,
                    )
                }
            )
            HorizontalDivider(
                modifier = Modifier
                    .padding(vertical = 8.dp)
                    .height(32.dp)
                    .background(MaterialTheme.colorScheme.secondaryContainer)
            )
            TextField(
                value = orderState.fullAddress,
                onValueChange = {
                    onAddressValueChanged(it)
                },
                modifier = Modifier.fillMaxWidth(),
                label = {
                    Text(text = stringResource(R.string.full_address))
                },
                trailingIcon = {
                    Icon(
                        painter =
                        if (orderState.isFullAddressCorrect)
                            painterResource(R.drawable.check_circle)
                        else
                            painterResource(R.drawable.circle),
                        contentDescription = null,
                        tint = if (orderState.isFullAddressCorrect)
                            Color.Green
                        else
                            MaterialTheme.colorScheme.outlineVariant,
                    )
                }
            )
            GenderSelection(
                selectedGender = orderState.gender,
                onGenderSelected = {
                    onGenderValueChanged(it)
                },
            )
        }
        val nextButtonEnabled by remember {
            derivedStateOf {
                orderState.isNameCorrect &&
                        orderState.isFamilyCorrect &&
                        orderState.isMobileCorrect &&
                        orderState.isPhoneCorrect &&
                        orderState.isFullAddressCorrect
            }
        }
        Button(
            onClick = {
                onNextClicked()
            },
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.secondaryContainer)
                .padding(16.dp),
            content = {
                Text(stringResource(R.string.next_step), fontWeight = FontWeight.Bold)
            },
            enabled = nextButtonEnabled,
            shape = RoundedCornerShape(8.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Green,
                contentColor = Color.White,
            )
        )
    }
}


@Composable
fun GenderSelection(
    selectedGender: Gender,
    onGenderSelected: (Gender) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            text = stringResource(R.string.gender),
            style = MaterialTheme.typography.labelLarge,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        SingleChoiceSegmentedButtonRow {
            SegmentedButton(
                onClick = {
                    onGenderSelected(Gender.Male)
                },
                selected = selectedGender == Gender.Male,
                label = {
                    Text(stringResource(R.string.male))
                },
                shape = SegmentedButtonDefaults.itemShape(index = 0, count = 2)
            )
            SegmentedButton(
                onClick = {
                    onGenderSelected(Gender.Female)
                },
                selected = selectedGender == Gender.Female,
                label = {
                    Text(stringResource(R.string.female))
                },
                shape = SegmentedButtonDefaults.itemShape(index = 1, count = 2)
            )
        }
    }
}

@Preview(
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO or Configuration.UI_MODE_TYPE_NORMAL,
    locale = "fa"
)
@Composable
private fun PreviewOrderInfoScreen() {
    Surface {
        OrderInfoScreen(
            orderState = OrderState(
                name = "رضا",
                isNameCorrect = true,
                family = "زرچی",
                isFamilyCorrect = true,
                phone = "0213456789",
                isPhoneCorrect = true,
                mobile = "09123456789",
                isMobileCorrect = true,
                fullAddress = "",
                isFullAddressCorrect = false,
                gender = Gender.Male,
                lat = 0.0,
                lng = 0.0,

                ),
            modifier = Modifier,
            onNameValueChanged = {},
            onFamilyValueChanged = {},
            onMobileValueChanged = {},
            onPhoneValueChanged = {},
            onAddressValueChanged = {},
            onGenderValueChanged = {},
            onNextClicked = {},
        )
    }
}