package com.example.test1.repo

import com.example.test1.network.api.MainApiInterface
import com.example.test1.network.request.Async
import com.example.test1.network.response.CatBreedModel
import com.example.test1.persistance.CatBreedsDao
import dagger.assisted.AssistedInject
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import javax.inject.Inject

class CatsRepository @Inject constructor(
    private val mainApiInterface: MainApiInterface,
    private val catBreedsDao: CatBreedsDao
) {

    suspend fun getCatBreeds() = flow {
        try {
            val result = mainApiInterface.getCatBreeds()
            if(result.isSuccessful){
                result.body()?.let {
                    emit(Async.Success(it))
                    saveToDB(it)
                } ?: run {
                    emit(Async.Error(1))
                }
            }else {
                emit(Async.Error(0))
            }
        }catch (e : Exception) {
            emit(Async.Error(100, e.message))
        }

    }

    suspend fun getBreedsFromDb()  = flow {
        emit(Async.Success(catBreedsDao.getBreeds()))
    }

    private fun saveToDB(breeds: List<CatBreedModel>) {
        CoroutineScope((SupervisorJob() + Dispatchers.IO)).apply {
            launch {
                catBreedsDao.insertBreeds(breeds)
            }
        }
    }
}