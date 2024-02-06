package com.example.beerpedia.usecases

import com.example.beerpedia.data.Repository
import com.example.beerpedia.domain.Beer
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetSingleBeerUseCase @Inject constructor(private val repository: Repository) {

    operator fun invoke(id: Int): Flow<Beer> = repository.getBeerById(id)
}