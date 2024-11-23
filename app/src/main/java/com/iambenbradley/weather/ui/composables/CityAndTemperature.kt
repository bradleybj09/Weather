package com.iambenbradley.weather.ui.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
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

    val typography = MaterialTheme.typography

    Column(
        modifier = modifier,
    ) {
        Row {
            Text(
                text = cityName,
                style = when (sizeVariant) {
                    CityAndTemperatureDisplaySize.Small -> typography.headlineMedium
                    CityAndTemperatureDisplaySize.Large -> typography.displayMedium
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
        Row {
            Text(
                text = temperature,
                style = when (sizeVariant) {
                    CityAndTemperatureDisplaySize.Small -> typography.headlineLarge
                    CityAndTemperatureDisplaySize.Large -> typography.displayLarge
                },
            )
            Icon(
                painter = painterResource(R.drawable.ic_ellipse),
                contentDescription = stringResource(R.string.location_indicator),
                tint = PrimaryText,
            )
        }
    }
}

enum class CityAndTemperatureDisplaySize {
    Small,
    Large,
}