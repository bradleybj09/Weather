package com.iambenbradley.weather.model.serial

import kotlinx.serialization.Serializable

@Serializable
data class SearchResponse(
    val id: Long,
    val name: String,
)
