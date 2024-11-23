package com.iambenbradley.weather.model.domain

data class CurrentWeather(
    val locationName: String,
    val currentTemperature: Int,
    val humidity: Int,
    val uv: Int,
    val feelsLike: Int,
    val conditionImageUrl: String,
    val conditionString : String,
)