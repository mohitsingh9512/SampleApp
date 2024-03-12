package com.example.test1.di.modules

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.example.test1.di.scope.ApplicationScope
import com.example.test1.network.api.MainApiInterface
import com.example.test1.persistance.BaseDatabase
import com.example.test1.persistance.CatBreedsDao
import dagger.Module
import dagger.Provides
import dagger.hilt.migration.DisableInstallInCheck
import retrofit2.Retrofit

@Module
@DisableInstallInCheck
class AppModule(private var application: Application) {

    @Provides
    @ApplicationScope
    fun providesApplication() : Application {
        return application
    }

    @Provides
    @ApplicationScope
    fun providesApplicationContext() : Context {
        return application.applicationContext
    }

    @Provides
    fun providesMainApiInterface(retrofit: Retrofit): MainApiInterface {
        return retrofit.create(MainApiInterface::class.java)
    }

    @Provides
    fun provideAppDb(@ApplicationScope app: Application): BaseDatabase {
        return Room
            .databaseBuilder(app, BaseDatabase::class.java, BaseDatabase.DATABASE_NAME)
            .allowMainThreadQueries()
            .fallbackToDestructiveMigration()
            .build()
    }
}
