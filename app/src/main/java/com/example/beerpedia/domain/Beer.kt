package com.example.beerpedia.domain

data class Beer (
    val id: Int = 0,
    val name: String = "",
    val image: String = "",
    val description: String = "",
    val abv: Float = 0f
){}

fun getBeers() = (1..20).map {
    Beer(
        id = it,
        name = "Beer name $it",
        image = "https://placekitten.com/300/300",
        description = "Description",
        abv = 5.0f
    )
}