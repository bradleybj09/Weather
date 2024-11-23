package com.iambenbradley.weather.ui.composables

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import com.iambenbradley.weather.R
import com.iambenbradley.weather.model.domain.CurrentWeather

@Composable
fun SearchResultsPage(
    searchResults: List<CurrentWeather>,
    onClickResult: (CurrentWeather) -> Unit,
    modifier: Modifier = Modifier,
) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.large_space_between)),
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier,
    ) {
        items(items = searchResults) { weather ->
            SearchResultCityWeather(
                currentWeather = weather,
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onClickResult(weather) }
            )
        }
    }
}