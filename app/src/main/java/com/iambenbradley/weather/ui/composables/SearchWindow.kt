package com.iambenbradley.weather.ui.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import com.iambenbradley.weather.R
import com.iambenbradley.weather.ui.theme.BackgroundLightGrey
import com.iambenbradley.weather.ui.theme.DetailHeaderText
import com.iambenbradley.weather.ui.theme.PrimaryText

@Composable
fun SearchWindow(
    text: String,
    onSearchTextChanged: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    TextField(
        value = text,
        placeholder = {
            Text(
                text = stringResource(R.string.search_placeholder),
                color = DetailHeaderText,
            )
        },
        onValueChange = { new ->
            onSearchTextChanged(new)
        },
        trailingIcon = {
            Icon(
                imageVector = Icons.Filled.Search,
                contentDescription = stringResource(R.string.search_placeholder),
                tint = DetailHeaderText,
            )
        },
        singleLine = true,
        shape = RoundedCornerShape(dimensionResource(R.dimen.detail_corner_radius)),
        colors = TextFieldDefaults.colors(
            focusedIndicatorColor = Color.Transparent,
            errorIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent,
            focusedTextColor = PrimaryText,
            unfocusedTextColor = PrimaryText,
            focusedTrailingIconColor = DetailHeaderText,
            disabledTrailingIconColor = DetailHeaderText,
            unfocusedTrailingIconColor = DetailHeaderText,
            focusedContainerColor = BackgroundLightGrey,
            unfocusedContainerColor = BackgroundLightGrey,
            errorContainerColor = BackgroundLightGrey,
            disabledContainerColor = BackgroundLightGrey,
        ),
        modifier = modifier
    )
}