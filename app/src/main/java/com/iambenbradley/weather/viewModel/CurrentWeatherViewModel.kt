package com.iambenbradley.weather.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.iambenbradley.weather.data.datastore.DataStoreRepository
import com.iambenbradley.weather.model.WeatherRepository
import com.iambenbradley.weather.model.domain.CurrentWeather
import com.iambenbradley.weather.util.MainDispatcher
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CurrentWeatherViewModel @Inject constructor(
    private val weatherRepository: WeatherRepository,
    private val dataStoreRepository: DataStoreRepository,
    @MainDispatcher private val mainDispatcher: CoroutineDispatcher,
) : ViewModel() {


    private val searchState = MutableStateFlow<LocationSearchState>(LocationSearchState.None)

    val appState = combine(
        searchState,
        dataStoreRepository.observeLocationPreference(),
    ) { search, location ->
        when (search) {
            LocationSearchState.None -> when (location) {
                null -> CurrentWeatherState.NoSelectedLocation
                else -> {
                    val weather = weatherRepository.getWeatherForLocation(location)
                    if (weather != null) {
                        CurrentWeatherState.Some(weather)
                    } else {
                        CurrentWeatherState.Error
                    }
                }
            }
            else -> search
        }
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(),
        initialValue = CurrentWeatherState.Loading,
    )

    private var searchJob: Job? = null

    fun search(search: String) {
        searchJob?.cancel()
        searchJob = viewModelScope.launch(mainDispatcher) {
            if (search.isEmpty()) {
                searchState.value = LocationSearchState.None
                return@launch
            }
            if (searchState.value == LocationSearchState.None) {
                searchState.value = LocationSearchState.Loading
            }
            when (val results = weatherRepository.searchForLocationsWithCurrentWeather(search)) {
                null -> searchState.value = LocationSearchState.Error
                else -> searchState.value = LocationSearchState.Some(results)
            }
        }
    }

    fun persistLocation(currentWeather: CurrentWeather) {
        viewModelScope.launch(mainDispatcher) {
            dataStoreRepository.setLocationPreference(currentWeather.locationName)
        }
    }
}

sealed interface AppState

sealed interface CurrentWeatherState : AppState {
    data object NoSelectedLocation : CurrentWeatherState
    data object Loading : CurrentWeatherState
    data class Some(val currentWeather: CurrentWeather) : CurrentWeatherState
    data object Error : CurrentWeatherState
}

sealed interface LocationSearchState : AppState  {
    data object Loading : LocationSearchState
    data class Some(val locations: List<CurrentWeather>) : LocationSearchState
    data object None : LocationSearchState
    data object Error : LocationSearchState
}