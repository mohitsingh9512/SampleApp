package com.example.test1.di.modules

import com.example.test1.persistance.BaseDatabase
import com.example.test1.persistance.CatBreedsDao
import dagger.Module
import dagger.Provides
import dagger.hilt.migration.DisableInstallInCheck

@Module
@DisableInstallInCheck
class ActivityModule {

    @Provides
    fun providesCatBreedDao(db: BaseDatabase): CatBreedsDao {
        return db.getCatBreedsDao()
    }

}

