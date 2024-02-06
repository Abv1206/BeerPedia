package com.example.beerpedia.data

import com.example.beerpedia.data.datasource.BeerLocalDataSource
import com.example.beerpedia.data.datasource.BeerRemoteDataSource
import com.example.beerpedia.domain.Beer
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

class RepositoryTest {

    @Mock
    lateinit var localDataSource: BeerLocalDataSource

    @Mock
    lateinit var remoteDataSource: BeerRemoteDataSource

    private lateinit var repository: Repository

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        repository = Repository(localDataSource, remoteDataSource)
    }

    @Test
    fun testRequestBeersSuccess() = runBlocking {
        val beers = listOf(Beer(1, "Beer1"), Beer(2, "Beer2"))
        `when`(remoteDataSource.findBeers()).thenReturn(beers)

        val result = repository.requestBeers()

        assertEquals(null, result)
    }

    @Test
    fun testRequestBeersEmptyList() = runBlocking {
        val beers = emptyList<Beer>()
        `when`(remoteDataSource.findBeers()).thenReturn(beers)

        val result = repository.requestBeers()

        assertTrue(result is Exception)
    }

    @Test
    fun testGetBeerById() = runBlocking {
        val beerId = 1
        val beer = Beer(beerId, "Beer1")
        `when`(localDataSource.findById(beerId)).thenReturn(flowOf(beer))

        val result = repository.getBeerById(beerId).first()

        assertEquals(beer, result)
    }

    @Test
    fun testFilterByName() = runBlocking {
        val name = "Beer"
        val filteredBeers = listOf(Beer(1, "Beer1"), Beer(2, "Beer2"))
        `when`(localDataSource.filterByName(name)).thenReturn(flowOf(filteredBeers))

        val result = repository.filterByName(name).first()

        assertEquals(filteredBeers, result)
    }


    @Test
    fun testGetBeersWithName() = runBlocking {
        val filteredBeers = listOf(Beer(1, "Beer1"), Beer(2, "Beer2"))
        `when`(localDataSource.filterByName("Beer")).thenReturn(flowOf(filteredBeers))

        var result: List<Beer>? = null
        repository.getBeers("Beer").collect {
            result = it
        }

        assertEquals(filteredBeers, result)
    }
}