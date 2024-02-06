package com.example.beerpedia.ui.screens.main

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import com.example.beerpedia.domain.Beer

@Composable
fun MainScreen(viewModel: MainViewModel, onBeerClick: (Beer) -> Unit) {
    Scaffold(
        topBar = { MainAppBar() }
    ) { padding ->
        Main(
            padding = padding,
            onBeerClick = onBeerClick,
            list = viewModel.beerList,
            searchText = viewModel.searchText,
            onSearchTextChanged = viewModel::onSearchTextChanged
        )
    }
}

@Composable
fun Main(
    padding: PaddingValues,
    onBeerClick: (Beer) -> Unit,
    list: List<Beer>,
    searchText: String,
    onSearchTextChanged: (String) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(padding)
    ) {
        SearchBar(searchText, onSearchTextChanged)
        BeerList(onBeerClick = onBeerClick, list)
    }
}
