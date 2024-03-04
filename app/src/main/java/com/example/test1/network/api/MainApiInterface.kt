package com.example.test1.network.api

import com.example.test1.network.response.CatBreedDetailModel
import com.example.test1.network.response.CatBreedModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MainApiInterface {

    @GET(BREEDS)
    suspend fun getCatBreeds(): Response<List<CatBreedModel>>

    @GET(API_CAT_END_POINT)
    suspend fun getCatBreedDetail(
        @Query(QUERY_ID) id: String
    ): Response<List<CatBreedDetailModel>>
}

const val BASE_URL = "https://api.thecatapi.com/v1/"
const val BREEDS = "breeds"
const val API_CAT_END_POINT = "images/search?breed_ids="
const val QUERY_ID = "i"