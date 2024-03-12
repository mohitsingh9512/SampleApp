package com.example.test1.repo

import com.example.listcomponent.network.Async
import com.example.test1.network.response.CatBreedModel
import kotlinx.coroutines.flow.Flow

interface CatsRepository {
    suspend fun getCatBreeds(): Flow<Async<List<CatBreedModel>>>
    suspend fun getBreedsFromDb(): Flow<Async.Success<List<CatBreedModel>>>
}