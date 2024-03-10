package com.example.test1.network.api

import com.example.test1.network.response.CatBreedModel
import retrofit2.Response
import retrofit2.http.GET

interface MainApiInterface {

    @GET(BREEDS)
    suspend fun getCatBreeds(): Response<List<CatBreedModel>>
}

const val BASE_URL = "https://api.thecatapi.com/v1/"
const val BREEDS = "breeds"
const val API_CAT_IMAGE = "https://cdn2.thecatapi.com/images/"
