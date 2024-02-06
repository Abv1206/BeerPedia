package com.example.beerpedia.ui.screens.main

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.beerpedia.domain.Beer
import com.example.beerpedia.usecases.GetBeersUseCase
import com.example.beerpedia.usecases.GetSingleBeerUseCase
import com.example.beerpedia.usecases.RequestBeersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.Dispatcher
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getBeersUseCase: GetBeersUseCase,
    private val requestBeersUseCase: RequestBeersUseCase
) : ViewModel(){

    var searchText by mutableStateOf("")
        private set
    var beerList by mutableStateOf<List<Beer>>(emptyList())
        private set

    init {
        viewModelScope.launch(Dispatchers.IO) {
            val error = requestBeersUseCase()
            /** If error is returned, show it on screen **/
            getList()
        }
    }

    fun onSearchTextChanged(text: String) {
        searchText = text
        viewModelScope.launch {
            getList()
        }
    }

    private suspend fun getList() {
        /** Show error if occurs **/
        withContext(Dispatchers.IO) {
            getBeersUseCase(searchText)
                .collect { beers -> beerList = beers }
        }
    }
}
