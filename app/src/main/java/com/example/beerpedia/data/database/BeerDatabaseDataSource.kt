package com.example.beerpedia.data.database

import com.example.beerpedia.data.datasource.BeerLocalDataSource
import com.example.beerpedia.domain.Beer
import com.example.beerpedia.data.database.Beer as DbBeer
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class BeerDatabaseDataSource @Inject constructor(private val beerDao: BeerDao) :
    BeerLocalDataSource {

    override val beers: Flow<List<Beer>>
        get() = beerDao.getAll().map { it.toDomainModel() }


    override fun filterByName(name: String): Flow<List<Beer>> = beerDao.filterByName(name).map { it.toDomainModel() }

    override fun findById(id: Int): Flow<Beer> = beerDao.findById(id).map { it.toDomainModel() }

    override suspend fun save(beers: List<Beer>): Exception? {
        try {
            beerDao.saveBeers(beers.fromDomainModel())
        } catch (e: Exception) {
            return e
        }
        return null
    }
}


private fun List<DbBeer>.toDomainModel(): List<Beer> = map { it.toDomainModel() }

private fun DbBeer.toDomainModel(): Beer = Beer(
    id, name, image, description, abv
)


private fun List<Beer>.fromDomainModel(): List<DbBeer> = map { it.fromDomainModel() }

private fun Beer.fromDomainModel(): DbBeer =
    DbBeer(
        id, name, image, description, abv
    )