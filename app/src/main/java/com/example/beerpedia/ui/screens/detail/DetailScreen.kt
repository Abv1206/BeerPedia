package com.example.beerpedia.ui.screens.detail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.beerpedia.domain.Beer
import com.example.beerpedia.domain.getBeers
import com.example.beerpedia.ui.BeerPediaApp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(onBackClick: () -> Unit, viewModel: DetailViewModel) {
    val beerItem = viewModel.beerItem.collectAsState(initial = Beer()).value
    BeerPediaApp {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text(text = beerItem.name) },
                    colors = TopAppBarDefaults.topAppBarColors (
                        containerColor = MaterialTheme.colorScheme.primary,
                        titleContentColor = MaterialTheme.colorScheme.onPrimary,
                        navigationIconContentColor = MaterialTheme.colorScheme.onPrimary,
                        actionIconContentColor = MaterialTheme.colorScheme.onSecondary
                    ),
                    navigationIcon = {
                        IconButton(onClick = onBackClick) {
                            Icon(
                                imageVector = Icons.Default.ArrowBack,
                                contentDescription = "Go back screen"
                            )
                        }
                    })
            }
        ) { padding ->
            Main(
                padding = padding,
                name = beerItem.name,
                image = beerItem.image,
                description = beerItem.description,
                abv = beerItem.abv
            )
        }
    }
}

@Composable
fun Main(
    padding: PaddingValues,
    name: String,
    image: String,
    description: String,
    abv: Float
) {
    Column(modifier = Modifier
        .padding(padding)
        .padding(horizontal = 12.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp)) {
        Box(modifier = Modifier
            .fillMaxWidth()
            .height(400.dp)
        ) {
            AsyncImage(
                model = image, contentDescription = "Beer image",
                modifier = Modifier
                    .fillMaxWidth(),
                alignment = Alignment.Center
            )
        }
        Text(text = description)
        Text(text = "ABV: $abv")
    }
}