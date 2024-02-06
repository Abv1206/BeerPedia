package com.example.beerpedia.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters

@Entity
class Beer (
    @PrimaryKey
    val id: Int,
    val name: String,
    val image: String,
    val description: String,
    val abv: Float
)
