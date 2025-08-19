package com.example.foodify.domain.repository


interface UserPreferencesRepository {
    suspend fun setFirstTime(firstTime: Boolean)
    suspend fun getFirstTime(): Result<Boolean>
}