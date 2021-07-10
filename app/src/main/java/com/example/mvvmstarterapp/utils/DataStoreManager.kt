package com.example.mvvmstarterapp.utils

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "main")

@Singleton
class DataStoreManager @Inject constructor(@ApplicationContext appContext: Context) {

    private val mainDataStore = appContext.dataStore

    val exampleCounterFlow: Flow<Int> = mainDataStore.data.map { preferences ->
        preferences[EXAMPLE_COUNTER] ?: 0
    }

    suspend fun incrementCounter() {
        mainDataStore.edit { settings ->
            val currentCounterValue = settings[EXAMPLE_COUNTER] ?: 0
            settings[EXAMPLE_COUNTER] = currentCounterValue + 1
        }
    }

    companion object {
        private val EXAMPLE_COUNTER = intPreferencesKey("example_counter")
    }
}