package com.iambenbradley.weather.ui.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import coil3.compose.AsyncImage
import com.iambenbradley.weather.R
import com.iambenbradley.weather.model.domain.CurrentWeather

@Composable
fun SelectedCityWeather(
    currentWeather: CurrentWeather,
    modifier: Modifier = Modifier,
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.large_space_between)),
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier,
    ) {
        AsyncImage(
            model = currentWeather.conditionImageUrl,
            contentDescription = currentWeather.conditionString,
            contentScale = ContentScale.FillWidth,
            modifier = Modifier.width(dimensionResource(R.dimen.large_weather_width)),
        )
        CityAndTemperature(
            sizeVariant = CityAndTemperatureDisplaySize.Large,
            cityName = currentWeather.locationName,
            temperature = currentWeather.currentTemperature.toString(),
        )
        DetailCard(
            humidity = currentWeather.humidity.toString(),
            uv = currentWeather.uv.toString(),
            feelsLike = currentWeather.feelsLike.toString()
        )
    }
}