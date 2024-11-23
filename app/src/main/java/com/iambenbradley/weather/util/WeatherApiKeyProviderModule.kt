package com.iambenbradley.weather.util

import com.iambenbradley.weather.BuildConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Qualifier
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class WeatherApiKeyProviderModule {

    @Provides
    @Singleton
    @ApiKey
    fun provideWeatherApiKey(): String {
        return BuildConfig.api_key
    }
}

@Qualifier
annotation class ApiKey