package com.example.test1.repo

import com.example.listcomponent.network.Async
import com.example.test1.di.scope.IoDispatcher
import com.example.test1.network.api.MainApiInterface
import com.example.test1.network.response.CatBreedModel
import com.example.test1.persistance.CatBreedsDao
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class CatsRepositoryImpl @Inject constructor(
    private val mainApiInterface: MainApiInterface,
    private val catBreedsDao: CatBreedsDao,
    @IoDispatcher private val dispatcher: CoroutineDispatcher,
    private val scope: CoroutineScope,
) : CatsRepository {

    override suspend fun getCatBreeds() : Flow<Async<List<CatBreedModel>>> = withContext(dispatcher) {
        flow {
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
    }

    override suspend fun getBreedsFromDb() : Flow<Async.Success<List<CatBreedModel>>> = withContext(dispatcher) {
        flow {
            emit(Async.Success(catBreedsDao.getBreeds()))
        }
    }


    private fun saveToDB(breeds: List<CatBreedModel>) {
        scope.launch {
            catBreedsDao.insertBreeds(breeds)
        }
    }
}