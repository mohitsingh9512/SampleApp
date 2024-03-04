package com.example.test1.persistance

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.test1.network.response.CatBreedModel

@Database(
    entities = [CatBreedModel::class],
    version = 1
)
abstract class BaseDatabase : RoomDatabase() {

    abstract fun getCatBreedsDao() : CatBreedsDao

    companion object {
        const val DATABASE_NAME: String = "app_db"
    }
}