package com.example.beerpedia.data.server

import retrofit2.Response
import retrofit2.http.GET

interface RemoteService {

    @GET("beers")
    suspend fun requestBeers(): Response<List<RemoteBeer>>
}