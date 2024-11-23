package com.iambenbradley.weather.data.datastore

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.iambenbradley.weather.util.IoDispatcher
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface DataStoreRepositoryBindingModule {
    @Binds
    @Singleton
    fun bindDataStoreRepository(impl: DataStoreRepositoryImpl): DataStoreRepository

}

interface DataStoreRepository {
    suspend fun setLocationPreference(location: String)
    fun observeLocationPreference(): Flow<String?>
}

class DataStoreRepositoryImpl @Inject constructor(
    private val datastore: DataStore<Preferences>,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
) : DataStoreRepository {

    companion object {
        private val LOCATION_KEY = stringPreferencesKey("location")
    }

    override suspend fun setLocationPreference(location: String) {
        withContext(ioDispatcher) {
            datastore.edit { prefs ->
                prefs[LOCATION_KEY] = location
            }
        }
    }

    override fun observeLocationPreference(): Flow<String?> {
        return datastore.data.map { prefs ->
            try {
                prefs[LOCATION_KEY]
            } catch (e: Exception) {
                null
            }
        }
            .flowOn(ioDispatcher)
            .distinctUntilChanged()
    }
}