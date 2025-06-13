package dk.example.geoapp.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dk.example.geoapp.Location
import dk.example.geoapp.LocationPermission
import dk.example.geoapp.LocationService
import dk.example.geoapp.domain.geoapiclient.GeoApiClient
import dk.example.geoapp.domain.geoapiclient.model.GeoItem
import dk.example.geoapp.domain.geoapiclient.model.GeoItemCode
import dk.example.geoapp.infrastructure.GeoApiClientImpl
import dk.example.geoapp.locationService
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

sealed class ViewState {
    object Loading : ViewState()
    data class DataLoaded(val data: ViewData) : ViewState()
    data class Error(val error: String) : ViewState()
}
data class ViewData(
    val items: List<GeoItem>,
    val nearestSection: NearestSection,
)

data class HomeUIState(
    val viewState: ViewState = ViewState.Loading,
    val fetchGeoItemsInFlight: Boolean = true,
    var presentDetailSheet: GeoItem? = null
)

sealed class NearestSection {
    data class Nearest(val item: GeoItem) : NearestSection()
    data object GoToSettings : NearestSection()
    data object GetPermission : NearestSection()
}

fun LiveHomeViewModel(rawBaseUrl: String): HomeViewModel {
    return HomeViewModel(
        geoApiClient = GeoApiClientImpl(
            rawBaseUrl = rawBaseUrl
        ),
        locationService = locationService()
    )
}

class HomeViewModel(
    private val geoApiClient: GeoApiClient,
    private val locationService: LocationService = locationService()
) : ViewModel() {
    private val _state = MutableStateFlow(HomeUIState())
    val state: StateFlow<HomeUIState> = _state.asStateFlow()

    init {
        fetchGeoItems()
        viewModelScope.launch {
            locationService.locationPermissionOnChange.collect { permission ->
                _state.update { current ->
                    when (val viewState = current.viewState) {
                        is ViewState.DataLoaded -> {
                            current.copy(
                                viewState = ViewState.DataLoaded(
                                    viewState.data.copy(
                                        nearestSection = getNearestSection(
                                            items = viewState.data.items
                                        ),
                                        items = viewState.data.items.sortByDistance(
                                            location = locationService.getLocation()
                                        )
                                    )
                                )
                            )
                        }
                        else -> current
                    }
                }
            }
        }
    }

    fun retryButtonTap() {
        fetchGeoItems()
    }

    fun allowLocationPermissionButtonTap() {
        locationService.requestLocationPermission()
    }

    fun navigateToSettingButtonTap() {

    }

    fun navigateToMapButtonTap() {

    }

    fun onTapItem(item: GeoItem) {
        _state.update { it.copy(presentDetailSheet = item)}
    }

    fun itemDetailSheetChanged(item: GeoItem?) {
        _state.update { it.copy(presentDetailSheet = item) }
    }

    private fun fetchGeoItems() {
        viewModelScope.launch {
            _state.update { it.copy(fetchGeoItemsInFlight = true) }

            geoApiClient.fetchGeoItems(
                types = listOf(
//                    GeoItemTypeId(GeoItemId.picnic),
//                    GeoItemTypeId(GeoItemId.toilet),
//                    GeoItemTypeId(GeoItemId.udsigt),
                    GeoItemCode.SHELTER,
//                    GeoItemTypeId(GeoItemId.tørvejr),
//                    GeoItemTypeId(GeoItemId.badested),
//                    GeoItemTypeId(GeoItemId.bålplads),
//                    GeoItemTypeId(GeoItemId.bænk),
//                    GeoItemTypeId(GeoItemId.holdeplads),
//                    GeoItemTypeId(GeoItemId.legeplads),
//                    GeoItemTypeId(GeoItemId.parkering),
//                    GeoItemTypeId(GeoItemId.teltplads),
                )
            ).fold(
                onSuccess = { items ->
                    _state.value = HomeUIState(
                        fetchGeoItemsInFlight = false,
                        viewState = ViewState.DataLoaded(
                            ViewData(
                                items = items.sortByDistance(location = locationService.getLocation()),
                                nearestSection = getNearestSection(items = items),
                            )
                        )
                    )
                },
                onFailure = {
                    _state.value = HomeUIState(
                        viewState = ViewState.Error("Something went wrong!"),
                        fetchGeoItemsInFlight = false
                    )
                }
            )
        }
    }

    fun getNearestSection(items: List<GeoItem>): NearestSection {
        return when (locationService.getLocationPermission()) {
            LocationPermission.authorized -> {
                val currentLoc = locationService.getLocation()
                val nearest = currentLoc?.let { loc ->
                    items.minByOrNull { it.distanceInKmTo(loc) ?: Double.MAX_VALUE }
                }

                return nearest?.let { NearestSection.Nearest(item = it) }
                    ?: NearestSection.GoToSettings
            }
            LocationPermission.denied, LocationPermission.restricted -> NearestSection.GoToSettings
            LocationPermission.notDetermined -> NearestSection.GetPermission
        }
    }
}

fun List<GeoItem>.sortByDistance(location: Location?): List<GeoItem> {
    return sortedBy { it.distanceInKmTo(location) }
}
