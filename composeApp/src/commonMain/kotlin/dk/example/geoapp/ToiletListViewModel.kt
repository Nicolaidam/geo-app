package dk.example.geoapp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dk.example.geoapp.api.GeoApiClient
import dk.example.geoapp.model.GeoItem
import dk.example.geoapp.model.Response
import dk.example.geoapp.model.toModel
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

//data class Navigation(
//
//)
//
//sealed class Sheet {
//    data class GeoItemDetail(val item: GeoItem) : NearestSection()
//}
//
//sealed class Stack {
//    data class ExploreFeature(val item: ExploreViewModel) : Stack()
//}
//

//class ExploreViewModel(
//    private val geoApi: ToiletAPI,
//    private val locationService: LocationService = locationService()
//) : ViewModel() {
//}

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
            when (val geosDto = geoApiClient.getGeoItems()) {
                is Response.Success -> {
                val items = geosDto.data.map { it.toModel(currentLocation = locationService.getLocation()) }
                    _state.value = HomeUIState(
                        fetchGeoItemsInFlight = false,
                        viewState = ViewState.DataLoaded(
                            ViewData(
                                items = items.sortByDistance(location = locationService.getLocation()),
                                nearestSection = getNearestSection(items = items),
                            )
                        )
                    )
                }
                is Response.Error -> {
                    _state.value = HomeUIState(
                        viewState = ViewState.Error("Something went wrong!"),
                        fetchGeoItemsInFlight = false
                    )
                }
            }
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
