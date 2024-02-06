package com.example.beerpedia.usecases

import com.example.beerpedia.data.Repository
import javax.inject.Inject

class GetBeersUseCase @Inject constructor(private val repository: Repository) {

    operator fun invoke(name: String) = repository.getBeers(name)
}