package com.example.test1.di.modules

import com.example.test1.repo.CatsRepository
import com.example.test1.repo.CatsRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.migration.DisableInstallInCheck

@Module
@DisableInstallInCheck
abstract class RepositoryModule {

    @Binds
    abstract fun bindCatRepository(repository: CatsRepositoryImpl): CatsRepository
}
