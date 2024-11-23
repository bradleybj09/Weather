package com.iambenbradley.weather.ui.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import com.iambenbradley.weather.R
import com.iambenbradley.weather.ui.theme.BackgroundLightGrey

@Composable
fun DetailCard(
    humidity: String,
    uv: String,
    feelsLike: String,
    modifier: Modifier = Modifier,
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .size(
                width = dimensionResource(R.dimen.detail_width),
                height = dimensionResource(R.dimen.detail_height),
            )
            .background(
                color = BackgroundLightGrey,
                shape = RoundedCornerShape(size = dimensionResource(R.dimen.detail_corner_radius)),
            )
            .padding(dimensionResource(R.dimen.detail_padding)),
    ) {
        DetailInfo(
            header = stringResource(R.string.humidity),
            data = "$humidity%",
        )
        DetailInfo(
            header = stringResource(R.string.uv),
            data = uv,
        )
        DetailInfo(
            header = stringResource(R.string.feels_like),
            data = "$feelsLike°",
        )
    }
}

@Composable
fun DetailInfo(
    header: String,
    data: String,
    modifier: Modifier = Modifier,
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier,
    ) {
        Text(
            text = header,
            style = MaterialTheme.typography.labelSmall,
        )
        Text(
            text = data,
            style = MaterialTheme.typography.labelMedium,
        )
    }
}