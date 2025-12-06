package com.tinhtx.theweather.ui.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tinhtx.theweather.data.PreferencesManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

data class SplashUiState(
    val isLoading: Boolean = true,
    val navigateToWelcome: Boolean = false,
    val navigateToHome: Boolean = false
)

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val preferencesManager: PreferencesManager
) : ViewModel() {

    private val _uiState = MutableStateFlow(SplashUiState())
    val uiState: StateFlow<SplashUiState> = _uiState.asStateFlow()

    init {
        checkFirstLaunch()
    }

    private fun checkFirstLaunch() {
        viewModelScope.launch {
            delay(3000) // 3 second splash delay
            
            val isFirstLaunch = preferencesManager.isFirstLaunch.first()
            
            _uiState.value = if (isFirstLaunch) {
                SplashUiState(isLoading = false, navigateToWelcome = true)
            } else {
                SplashUiState(isLoading = false, navigateToHome = true)
            }
        }
    }
}
