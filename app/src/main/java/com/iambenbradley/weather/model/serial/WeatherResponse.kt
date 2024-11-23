package com.iambenbradley.weather.model.serial

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class WeatherResponse(
    val location: LocationResponse? = null,
    val current: CurrentWeatherResponse? = null,
)

@Serializable
data class LocationResponse(
    val name: String? = null,
)

@Serializable
data class CurrentWeatherResponse(
    @SerialName("temp_c")
    val tempC: Double? = null,
    @SerialName("temp_f")
    val tempF: Double? = null,
    val humidity: Int? = null,
    val uv: Double? = null,
    @SerialName("feelslike_c")
    val feelsLikeC: Double? = null,
    @SerialName("feelslike_f")
    val feelsLikeF: Double? = null,
    val condition: ConditionResponse? = null,
)

@Serializable
data class ConditionResponse(
    val text: String? = null,
    val icon: String? = null,
)