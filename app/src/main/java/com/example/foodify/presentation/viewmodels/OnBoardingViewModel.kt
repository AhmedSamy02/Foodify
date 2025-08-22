package com.example.foodify.presentation.viewmodels

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.foodify.R
import com.example.foodify.presentation.theme.MainColor
import com.example.foodify.presentation.theme.OnBoarding2Color
import com.example.foodify.presentation.theme.OnBoarding3Color
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class OnBoardingViewModel : ViewModel() {
    init {
        emulateAnimation()
    }

    var index = 0
    private val colors = listOf(MainColor, OnBoarding2Color, OnBoarding3Color)
    private val texts = listOf(
        "Your personal guide  to be  a chef",
        "Share  the  Love, Share the Recipe",
        "Foodify Your Global  Kitchen"
    )
    private val images = listOf(
        listOf(R.drawable.dish1_1, R.drawable.dish1_2, R.drawable.dish1_3),
        listOf(R.drawable.dish2_1, R.drawable.dish2_2, R.drawable.dish2_3),
        listOf(R.drawable.dish3_1, R.drawable.dish3_2, R.drawable.dish3_3)
    )
    val color = mutableStateOf(colors[0])
    val text = mutableStateOf(texts[0])
    val imagesList = mutableStateOf(images[0])
    val showButton = mutableStateOf(false)

    private fun emulateAnimation() {
        viewModelScope.launch {
            while (index < 2) {
                delay(3000)
                imagesList.value = emptyList()
                index++
                color.value = colors[index]
                text.value = texts[index]
                imagesList.value = emptyList()
                delay(20)
                imagesList.value = images[index]

            }
            delay(4000)
            showButton.value = true
        }
    }
}