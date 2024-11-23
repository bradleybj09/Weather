package com.iambenbradley.weather.ui.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import com.iambenbradley.weather.R

@Composable
fun NoLocation(
    modifier: Modifier = Modifier,
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.standard_space_between)),
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier,
    ) {
        Text(
            text = stringResource(R.string.no_city_selected),
            style = MaterialTheme.typography.displayMedium,
        )
        Text(
            stringResource(R.string.search_for_city),
            style = MaterialTheme.typography.displaySmall,
        )
    }
}