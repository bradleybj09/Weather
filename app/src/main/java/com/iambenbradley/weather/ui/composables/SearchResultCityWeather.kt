package com.iambenbradley.weather.ui.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import coil3.compose.AsyncImage
import com.iambenbradley.weather.R
import com.iambenbradley.weather.model.domain.CurrentWeather
import com.iambenbradley.weather.ui.theme.BackgroundLightGrey

@Composable
fun SearchResultCityWeather(
    currentWeather: CurrentWeather,
    modifier: Modifier = Modifier,
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .background(
                color = BackgroundLightGrey,
                shape = RoundedCornerShape(dimensionResource(R.dimen.detail_corner_radius))
            )
            .padding(dimensionResource(R.dimen.detail_padding)),
    ) {
        CityAndTemperature(
            sizeVariant = CityAndTemperatureDisplaySize.Small,
            cityName = currentWeather.locationName,
            temperature = currentWeather.currentTemperature.toString(),
        )
        AsyncImage(
            model = currentWeather.conditionImageUrl,
            contentDescription = currentWeather.conditionString,
            contentScale = ContentScale.FillWidth,
            modifier = Modifier.width(dimensionResource(R.dimen.small_weather_width)),
        )
    }
}