package com.example.beerpedia.data.database

import androidx.room.Database
import androidx.room.RoomDatabase


@Database(entities = [Beer::class], version = 1, exportSchema = false)
abstract class BeerDatabase: RoomDatabase() {

    abstract fun beerDao(): BeerDao
}