package com.example.foodify.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.foodify.domain.repository.UserPreferencesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(private val repo: UserPreferencesRepository) : ViewModel() {
    suspend fun checkFirstTime(): Boolean {
        if(repo.getFirstTime().getOrNull()?:true){
            repo.setFirstTime(false)
            return true
        }else{
            return false
        }
    }
}