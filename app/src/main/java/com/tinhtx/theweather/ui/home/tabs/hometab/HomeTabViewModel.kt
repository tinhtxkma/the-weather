package com.tinhtx.theweather.ui.home.tabs.hometab

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

data class HomeTabUiState(
    val weatherInfo: String = "Weather information will appear here"
)

@HiltViewModel
class HomeTabViewModel @Inject constructor() : ViewModel() {

    private val _uiState = MutableStateFlow(HomeTabUiState())
    val uiState: StateFlow<HomeTabUiState> = _uiState.asStateFlow()
}
