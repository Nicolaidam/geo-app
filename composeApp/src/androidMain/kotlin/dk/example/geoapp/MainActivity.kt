package dk.example.geoapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import dk.example.geoapp.infrastructure.GeoApiClientImpl

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)

        setContent {
            App(api = GeoApiClientImpl(
                rawBaseUrl = "http://10.0.2.2:8080",
            ))
        }
    }
}

//@Preview
//@Composable
//fun AppAndroidPreview() {
//    App(
//        api = object : GeoApiClient {
//            override suspend fun getGeoItems(): Response<List<GeoItemDto>> {
//                delay(3000)
//                return Response.Success(listOf(
//                    GeoItemDto(
//                        id = "",
//                        name = "",
//                        description = " yooyoyoyoyoyoyoy",
//                        latitude = 1.9,
//                        longitude = 2.8,
//                        paymentRequired = true,
//                        photoUrls = emptyList(),
//                        geoItemType =
//                    )
//                ))
//            }
//        }
//    )
//}
