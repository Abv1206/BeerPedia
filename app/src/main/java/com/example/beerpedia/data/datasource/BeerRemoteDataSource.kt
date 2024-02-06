package com.example.beerpedia.data.datasource

import com.example.beerpedia.domain.Beer

interface BeerRemoteDataSource {
    suspend fun findBeers(): List<Beer>?
}