package com.example.foodify.data.repository

import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.core.IOException
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import com.example.foodify.domain.repository.UserPreferencesRepository
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class UserPreferencesRepositoryImpl @Inject constructor(
    private val preferences: DataStore<Preferences>
) : UserPreferencesRepository {

    override suspend fun setFirstTime(firstTime: Boolean) {
        Result.runCatching {
            preferences.edit { preferences ->
                preferences[FIRST_TIME] = firstTime
            }
        }
    }

    override suspend fun getFirstTime(): Result<Boolean> {
        return Result.runCatching {
            val flow = preferences.data
                .catch { exception ->
                    if (exception is IOException) {
                        emit(emptyPreferences())
                    } else {
                        throw exception
                    }
                }
                .map { preferences ->
                    preferences[FIRST_TIME]
                }
            val value = flow.firstOrNull() ?: true
            value
        }
    }

    companion object {
        val FIRST_TIME = booleanPreferencesKey(name = "firstTime")
    }
}