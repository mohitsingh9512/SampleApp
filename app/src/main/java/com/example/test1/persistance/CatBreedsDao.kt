package com.example.test1.persistance

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.test1.network.response.CatBreedModel
import javax.inject.Singleton

@Singleton
@Dao
interface CatBreedsDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertBreeds(catBreeds : List<CatBreedModel>)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertBreed(catBreed :CatBreedModel)

    @Query("Select * from catbreedmodel")
    suspend fun getBreeds() : List<CatBreedModel>

    @Query("Delete FROM catbreedmodel")
    suspend fun clearAll()
}