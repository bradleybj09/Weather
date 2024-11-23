package com.iambenbradley.weather.ui.composables

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.iambenbradley.weather.R
import com.iambenbradley.weather.ui.theme.PrimaryText
import com.iambenbradley.weather.viewModel.CurrentWeatherState
import com.iambenbradley.weather.viewModel.CurrentWeatherViewModel
import com.iambenbradley.weather.viewModel.LocationSearchState

@Composable
fun WeatherPage(
    modifier: Modifier = Modifier,
    viewModel: CurrentWeatherViewModel = hiltViewModel(),
) {
    val appState by viewModel.appState.collectAsState()

    var searchText by remember { mutableStateOf("") }

    Box(
        modifier = modifier
            .fillMaxSize()
    ) {
        SearchWindow(
            text = searchText,
            onSearchTextChanged = { text ->
                viewModel.search(text)
                searchText = text
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = dimensionResource(R.dimen.search_padding))
                .padding(top = dimensionResource(R.dimen.top_padding))
        )
        when (val state = appState) {
            CurrentWeatherState.Error -> NoLocation(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = dimensionResource(R.dimen.no_city_padding))
                    .padding(top = dimensionResource(R.dimen.no_city_padding_top))
            )

            CurrentWeatherState.Loading -> CircularProgressIndicator(
                color = PrimaryText,
                modifier = Modifier
                    .width(dimensionResource(R.dimen.detail_height))
                    .align(Alignment.Center)
            )

            CurrentWeatherState.NoSelectedLocation -> NoLocation(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = dimensionResource(R.dimen.no_city_padding))
                    .padding(top = dimensionResource(R.dimen.no_city_padding_top))
            )

            is CurrentWeatherState.Some -> SelectedCityWeather(
                currentWeather = state.currentWeather,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = dimensionResource(R.dimen.city_padding_top))
            )

            LocationSearchState.Error -> Unit

            LocationSearchState.Loading -> Unit

            LocationSearchState.None -> Unit // We should never see this

            is LocationSearchState.Some -> SearchResultsPage(
                searchResults = state.locations,
                onClickResult = { text ->
                    viewModel.persistLocation(text)
                    viewModel.search("")
                    searchText = ""
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = dimensionResource(R.dimen.detail_padding))
                    .padding(top = dimensionResource(R.dimen.search_results_padding_top))
            )
        }
    }

}