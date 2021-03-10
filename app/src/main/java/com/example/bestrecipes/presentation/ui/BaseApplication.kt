package com.example.bestrecipes.presentation.ui

import android.app.Application
import androidx.compose.runtime.mutableStateOf
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class BaseApplication: Application() {

    // should be saved to datastore or cache
    val isDark = mutableStateOf(false)

    fun toggleTheme(){
        isDark.value = !isDark.value
    }
}