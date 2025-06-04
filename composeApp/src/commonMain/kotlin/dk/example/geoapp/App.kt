package dk.example.geoapp

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.material3.lightColorScheme
import androidx.compose.ui.graphics.Color
import dk.example.geoapp.api.GeoApiClient

@Composable
fun App(
    api: GeoApiClient,
    viewModel: HomeViewModel = remember { HomeViewModel(
        geoApiClient = api
    ) }
) {
    MaterialTheme(
        colorScheme = lightColorScheme(
            background = Color(0xFFE4EBF0),
            surface = Color(0xFFE4EBF0),
        )
    ) {
        HomeScreen(viewModel = viewModel)
    }
}

@Composable
fun HomeScreen(viewModel: HomeViewModel) {
    HomeScreen(
        state = viewModel.state.collectAsState().value,
        retryButtonTap = { viewModel.retryButtonTap() }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun HomeScreen(state: HomeUIState, retryButtonTap: () -> Unit) {
    Scaffold(
        topBar = {
            LargeTopAppBar(
                title = { Text("Toilets nearby") },
            )
        },
    ) { paddingValues ->
//        if (state.isLoading) {
//            Box(contentAlignment = Alignment.Center, modifier = Modifier
//                .padding(paddingValues)
//                .fillMaxSize(),
//            ) {
//                CircularProgressIndicator()
//            }
//        } else {
//            Column(
//                Modifier
//                    .padding(paddingValues)
//                    .padding(horizontal = 24.dp)
//                    .fillMaxWidth()
//                    .verticalScroll(rememberScrollState())
//                    .clip(RoundedCornerShape(16.dp))
//                    .background(Color.White),
//            ) {
//                state.geos?.forEach {
//                    Surface(
//                        modifier = Modifier
//                            .padding(horizontal = 16.dp)
//                            .fillMaxWidth(),
//                        color = Color.White,
//                    ) {
//                        Text(
//                            text = "${it.description}",
//                            modifier = Modifier.padding(16.dp),
//                            style = MaterialTheme.typography.bodyLarge
//                        )
//                    }
//                }
//            }
//        }
    }
}

//@Preview
//@Composable
//private fun DataPreview() {
//    MaterialTheme(
//        colorScheme = lightColorScheme(
//            background = Color(0xFFE4EBF0),
//            surface = Color(0xFFE4EBF0),
//        )
//    ) {
//        ToiletListScreen(
//            state = ToiletListUIState(
//                isLoading = false,
//                error = "",
//                geos = listOf(
//                    GeoItemDto(
//                        id = "",
//                        name = "",
//                        description = " Yo Jo",
//                        latitude = 1.9,
//                        longitude = 2.8,
//                        paymentRequired = true,
//                        photoUrls = emptyList(),
//                    ),
//                    GeoItemDto(
//                        id = "",
//                        name = "",
//                        description = "Yo Bro",
//                        latitude = 1.9,
//                        longitude = 2.8,
//                        paymentRequired = true,
//                        photoUrls = emptyList(),
//                    ),
//                ),
//            ),
//            retryButtonTap = {},
//        )
//    }
//}
//
//@Preview
//@Composable
//private fun LoadingPreview() {
//    MaterialTheme(
//        colorScheme = lightColorScheme(
//            background = Color(0xFFE4EBF0),
//            surface = Color(0xFFE4EBF0),
//        )
//    ) {
//        ToiletListScreen(
//            state = ToiletListUIState(
//                isLoading = true,
//                error = "",
//                geos = emptyList(),
//            ),
//            retryButtonTap = {},
//        )
//    }
//}
//
//@Preview
//@Composable
//private fun ErrorPreview() {
//    MaterialTheme(
//        colorScheme = lightColorScheme(
//            background = Color(0xFFE4EBF0),
//            surface = Color(0xFFE4EBF0),
//        )
//    ) {
//        ToiletListScreen(
//            state = ToiletListUIState(
//                isLoading = false,
//                error = "Something went wrong!!!",
//                geos = emptyList(),
//            ),
//            retryButtonTap = {},
//        )
//    }
//}


