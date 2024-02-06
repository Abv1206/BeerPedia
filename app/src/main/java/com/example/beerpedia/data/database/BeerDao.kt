package com.example.beerpedia.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface BeerDao {

    @Query("Select * FROM Beer")
    fun getAll(): Flow<List<Beer>>

    @Query("Select * FROM Beer WHERE name LIKE '%' || :name || '%'")
    fun filterByName(name: String): Flow<List<Beer>>

    @Query("Select * FROM Beer WHERE id = :id")
    fun findById(id: Int): Flow<Beer>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveBeers(beers: List<Beer>)
}