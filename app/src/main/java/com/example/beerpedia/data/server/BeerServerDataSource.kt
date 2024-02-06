package com.example.beerpedia.data.server

import com.example.beerpedia.data.datasource.BeerRemoteDataSource
import com.example.beerpedia.domain.Beer
import javax.inject.Inject

class BeerServerDataSource @Inject constructor(
    private val remoteService: RemoteService
) : BeerRemoteDataSource {

    override suspend fun findBeers(): List<Beer>? {
        return if (remoteService.requestBeers().isSuccessful) {
            remoteService
                .requestBeers()
                .body()
                ?.toDomainModel()
        } else {
            emptyList()
        }
    }

}

private fun List<RemoteBeer>.toDomainModel(): List<Beer> = map { it.toDomainModel() }

private fun RemoteBeer.toDomainModel(): Beer = Beer (
    id = id,
    name = name,
    image = image,
    description = description,
    abv = abv
)