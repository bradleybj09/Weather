package com.iambenbradley.weather.model

import com.iambenbradley.weather.model.domain.CurrentWeather
import com.iambenbradley.weather.model.serial.WeatherResponse
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.math.roundToInt

@Module
@InstallIn(SingletonComponent::class)
interface SerialToDomainMapperBindingModule {

    @Binds
    @Singleton
    fun bindSerialToDomainMapper(impl: SerialToDomainMapperImpl): SerialToDomainMapper
}

interface SerialToDomainMapper {

    fun toCurrentWeather(serial: WeatherResponse): CurrentWeather
}

class SerialToDomainMapperImpl @Inject constructor(): SerialToDomainMapper {

    override fun toCurrentWeather(serial: WeatherResponse): CurrentWeather {
        return with (serial) {
            CurrentWeather(
                locationName = location?.name.orEmpty(),
                currentTemperature = current?.tempF?.roundToInt() ?: 0,
                humidity = current?.humidity ?: 0,
                uv = current?.uv?.roundToInt() ?: 0,
                feelsLike = current?.feelsLikeF?.roundToInt() ?: 0,
                conditionImageUrl = current?.condition?.icon?.let { brokenString ->
                    // I'm not exactly sure why coil is struggling with this, but it is.
                    "https:$brokenString"
                } ?: "",
                conditionString = current?.condition?.text.orEmpty()
            )
        }
    }
}