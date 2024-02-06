package com.example.beerpedia.ui.screens.detail

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.beerpedia.ui.navigation.NavArg
import com.example.beerpedia.usecases.GetSingleBeerUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val getSingleBeerUseCase: GetSingleBeerUseCase
) : ViewModel(){

    private val beerId = savedStateHandle[NavArg.BeerId.key] ?: 1
    var beerItem = getSingleBeerUseCase(beerId)
        private set

}