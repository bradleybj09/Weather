package com.iambenbradley.weather.data.api

import com.iambenbradley.weather.model.serial.SearchResponse
import com.iambenbradley.weather.model.serial.WeatherResponse
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import javax.inject.Singleton

interface WeatherService {

    @GET("current.json")
    suspend fun getWeatherForLocation(
        @Query("key") key: String,
        @Query("q") query: String,
        @Query("aqi") aqi: String,
    ): Response<WeatherResponse>

    @GET("search.json")
    suspend fun getLocations(
        @Query("key") key: String,
        @Query("q") query: String,
    ): Response<List<SearchResponse>>
}

@Module
@InstallIn(SingletonComponent::class)
class WeatherServiceProviderModule {

    @Suppress("JSON_FORMAT_REDUNDANT")
    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit = Retrofit.Builder()
        .baseUrl("https://api.weatherapi.com/v1/")
        .addConverterFactory(
            Json {
                ignoreUnknownKeys = true
                isLenient = true
            }.asConverterFactory("application/json".toMediaType()),
        )
        .build()

    @Provides
    @Singleton
    fun provideWeatherService(retrofit: Retrofit): WeatherService = retrofit
        .create(WeatherService::class.java)
}
