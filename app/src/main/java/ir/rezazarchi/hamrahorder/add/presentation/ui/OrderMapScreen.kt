package ir.rezazarchi.hamrahorder.add.presentation.ui

import android.view.LayoutInflater
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.mapbox.mapboxsdk.Mapbox
import com.mapbox.mapboxsdk.annotations.MarkerOptions
import com.mapbox.mapboxsdk.camera.CameraPosition
import com.mapbox.mapboxsdk.geometry.LatLng
import com.mapbox.mapboxsdk.maps.MapView
import com.mapbox.mapboxsdk.maps.MapboxMap
import com.mapbox.mapboxsdk.maps.Style
import com.mapbox.mapboxsdk.module.http.HttpRequestUtil
import ir.rezazarchi.hamrahorder.R
import ir.rezazarchi.hamrahorder.add.domain.model.SelectedLocation
import ir.rezazarchi.hamrahorder.core.maputils.MAP_CLIENT
import ir.rezazarchi.hamrahorder.ui.theme.HamrahOrderTheme
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import org.koin.compose.koinInject
import org.koin.core.qualifier.named


@Composable
fun OrderMapScreen(
    modifier: Modifier = Modifier,
    onLocationSelected: (SelectedLocation) -> Unit,
    onNextClicked: () -> Unit
) {
    Box(modifier = modifier) {
        MabBoxView(
            modifier = Modifier.fillMaxSize(),
            onLocationSelected = onLocationSelected,
        )
        Text(
            text = stringResource(R.string.select_your_location),
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.TopCenter)
                .background(MaterialTheme.colorScheme.background.copy(alpha = 0.7f))
                .padding(horizontal = 8.dp, vertical = 16.dp),
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.onSecondaryContainer
        )
        Button(
            onClick = {
                onNextClicked()
            },
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
                .padding(16.dp),
            content = {
                Text(stringResource(R.string.confirm_location), fontWeight = FontWeight.Bold)
            },
            enabled = true,
            shape = RoundedCornerShape(8.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Green,
                contentColor = Color.White,
            )
        )
    }
}

@Composable
fun MabBoxView(
    modifier: Modifier,
    onLocationSelected: (SelectedLocation) -> Unit,
) {
    val coroutineScope = rememberCoroutineScope()
    Mapbox.getInstance(LocalContext.current)
    val mapClient = koinInject<OkHttpClient>(qualifier = named(MAP_CLIENT))
    val mapStyle = getMapStyle()
    AndroidView(
        modifier = modifier,
        factory = { context ->
            LayoutInflater.from(context).inflate(R.layout.layout_maps, null)
        },
        update = { view ->
            val mapView: MapView = view.findViewById(R.id.mapView)
            coroutineScope.launch {
                HttpRequestUtil.setOkHttpClient(
                    mapClient
                )
                mapView.getMapAsync { mapboxMap: MapboxMap ->
                    mapboxMap.setStyle(Style.Builder().fromUri(mapStyle))
                    val cameraPosition: CameraPosition = CameraPosition.Builder()
                        .target(LatLng(35.757565, 51.409970))
                        .zoom(15.0)
                        .build()
                    mapboxMap.cameraPosition = cameraPosition
                    mapboxMap.addOnMapClickListener { point ->
                        onLocationSelected(SelectedLocation(point.latitude, point.longitude))
                        mapboxMap.clear()
                        mapboxMap.addMarker(MarkerOptions().position(point))
                        true
                    }
                }
            }
        }
    )
}

@Composable
private fun getMapStyle() = if (isSystemInDarkTheme())
    "https://map.ir/vector/styles/main/mapir-style-dark.json"
else
    "https://map.ir/vector/styles/main/main_mobile_style.json"

@Preview
@Composable
private fun PreviewOrderMapScreen() {
    HamrahOrderTheme {
        OrderMapScreen(
            onLocationSelected = {},
            onNextClicked = {},
        )
    }

}