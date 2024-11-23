package com.iambenbradley.weather.ui.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.iambenbradley.weather.R

@Composable
fun NoLocation(
    modifier: Modifier = Modifier,
) {

    val typography = MaterialTheme.typography

    Column(
        modifier = modifier
    ) {
        Text(
            text = stringResource(R.string.no_city_selected),
            style = typography.displayMedium,
        )
        Text(
            stringResource(R.string.search_for_city),
            style = typography.displayMedium,
        )
    }
}