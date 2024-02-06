package com.example.beerpedia.usecases

import com.example.beerpedia.data.Repository
import javax.inject.Inject

class RequestBeersUseCase @Inject constructor(private val repository: Repository) {

    suspend operator fun invoke(): Exception? = repository.requestBeers()
}