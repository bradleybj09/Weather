package com.iambenbradley.weather.model

import com.iambenbradley.weather.data.api.WeatherService
import com.iambenbradley.weather.model.domain.CurrentWeather
import com.iambenbradley.weather.util.ApiKey
import com.iambenbradley.weather.util.IoDispatcher
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface WeatherRepositoryBindingModule {
    
    @Binds
    @Singleton
    fun bindWeatherRepository(impl: WeatherRepositoryImpl): WeatherRepository
}

interface WeatherRepository {
    suspend fun getWeatherForLocation(
        location: String,
    ): CurrentWeather?

    suspend fun searchForLocationsWithCurrentWeather(
        query: String,
    ): List<CurrentWeather>?
}

class WeatherRepositoryImpl @Inject constructor(
    private val service: WeatherService,
    private val mapper: SerialToDomainMapper,
    @ApiKey private val apiKey: String,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
) : WeatherRepository {

    override suspend fun getWeatherForLocation(
        location: String,
    ): CurrentWeather? {
        return withContext(ioDispatcher) {
            service.getWeatherForLocation(
                key = apiKey,
                query = location,
                aqi = "no",
            ).body()?.let { mapper.toCurrentWeather(it) }
        }
    }

    override suspend fun searchForLocationsWithCurrentWeather(
        query: String,
    ): List<CurrentWeather>? {
        return withContext(ioDispatcher) {
            service.getLocations(
                key = apiKey,
                query = query,
            ).body()?.mapNotNull { location ->
                service.getWeatherForLocation(
                    key = apiKey,
                    query = location.name,
                    aqi = "no",
                ).body()?.let { mapper.toCurrentWeather(it) }
            }
        }
    }
}
