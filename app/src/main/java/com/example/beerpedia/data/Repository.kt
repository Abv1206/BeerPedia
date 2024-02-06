package com.example.beerpedia.data

import com.example.beerpedia.data.datasource.BeerLocalDataSource
import com.example.beerpedia.data.datasource.BeerRemoteDataSource
import com.example.beerpedia.domain.Beer
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class Repository @Inject constructor(
    private val localDataSource: BeerLocalDataSource,
    private val remoteDataSource: BeerRemoteDataSource){

    val beers = localDataSource.beers

    suspend fun requestBeers(): Exception? {
        val beers = remoteDataSource.findBeers()
        if (beers.isNullOrEmpty()) {
            return Exception()
        } else {
            localDataSource.save(beers)
        }
        return null
    }

    fun getBeerById(id: Int): Flow<Beer> = localDataSource.findById(id)

    fun filterByName(name: String): Flow<List<Beer>> = localDataSource.filterByName(name)

    fun getBeers(name: String): Flow<List<Beer>> {
        return if (name.isEmpty()) beers else filterByName(name)
    }
}