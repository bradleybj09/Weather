package com.iambenbradley.weather.ui.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.iambenbradley.weather.R
import com.iambenbradley.weather.ui.theme.PrimaryText

@Composable
fun CityAndTemperature(
    sizeVariant: CityAndTemperatureDisplaySize,
    cityName: String,
    temperature: String,
    modifier: Modifier = Modifier,
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.large_space_between)),
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier,
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.standard_space_between)),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                text = cityName,
                style = when (sizeVariant) {
                    CityAndTemperatureDisplaySize.Small -> MaterialTheme.typography.headlineMedium
                    CityAndTemperatureDisplaySize.Large -> MaterialTheme.typography.displayMedium
                },
            )
            if (sizeVariant == CityAndTemperatureDisplaySize.Large) {
                Icon(
                    painter = painterResource(R.drawable.ic_location),
                    contentDescription = stringResource(R.string.location_indicator),
                    tint = PrimaryText,
                )
            }
        }
        Row(
            horizontalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.standard_space_between)),
            verticalAlignment = Alignment.Top,
        ) {
            Text(
                text = "$temperature°",
                style = when (sizeVariant) {
                    CityAndTemperatureDisplaySize.Small -> MaterialTheme.typography.headlineLarge
                    CityAndTemperatureDisplaySize.Large -> MaterialTheme.typography.displayLarge
                },
            )
        }
    }
}

enum class CityAndTemperatureDisplaySize {
    Small,
    Large,
}