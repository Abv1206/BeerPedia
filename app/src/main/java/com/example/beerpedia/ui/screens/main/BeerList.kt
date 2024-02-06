package com.example.beerpedia.ui.screens.main

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.beerpedia.domain.Beer

@Composable
fun BeerList (onBeerClick: (Beer) -> Unit, list: List<Beer>) {
    LazyVerticalGrid(columns = GridCells.Fixed(3)) {

        items(list) {
            BeerListItem(onClick = { onBeerClick(it) } , it)
        }
    }
}


@Composable
fun BeerListItem (onClick: () -> Unit, item: Beer) {
    Column (
        modifier = Modifier
            .padding(6.dp)
            .border(1.dp, Color.DarkGray)
            .clickable ( onClick = onClick )
    ){
        Box(modifier = Modifier
            .height(250.dp)
            .padding(4.dp)
            .fillMaxWidth()) {
            AsyncImage(
                model = item.image,
                contentDescription = null,
                modifier = Modifier.fillMaxSize(),
                alignment = Alignment.Center,
                contentScale = ContentScale.Fit
            )
        }
        Box(modifier = Modifier
            .height(50.dp)
            .fillMaxWidth()) {
            Text(
                text = item.name,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center
            )
        }
    }
}