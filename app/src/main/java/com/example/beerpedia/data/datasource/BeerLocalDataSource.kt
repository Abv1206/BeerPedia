package com.example.beerpedia.data.datasource

import com.example.beerpedia.domain.Beer
import kotlinx.coroutines.flow.Flow

interface BeerLocalDataSource {
    val beers: Flow<List<Beer>>

    fun findById(id: Int): Flow<Beer>
    fun filterByName(name: String): Flow<List<Beer>>

    suspend fun save(beers: List<Beer>): Exception?

}