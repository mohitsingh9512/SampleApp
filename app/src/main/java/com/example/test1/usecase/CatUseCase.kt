package com.example.test1.usecase

import android.content.Context
import com.example.listcomponent.network.Async
import com.example.test1.di.scope.ApplicationScope
import com.example.test1.extensions.isNetworkAvailable
import com.example.test1.repo.CatsRepository
import com.example.test1.ui.viewholder.uimodel.CatBreedDataModel
import com.example.test1.ui.viewholder.uimodel.toUIModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject

class CatUseCase @Inject constructor(
    @ApplicationScope private val context: Context,
    private val repository: CatsRepository
) {

    private val _breeds = MutableStateFlow<Async<List<CatBreedDataModel>>>(Async.None)
    val breeds: StateFlow<Async<List<CatBreedDataModel>>> = _breeds

    suspend fun getBreeds()  {
        withContext(Dispatchers.IO){
            if(!context.isNetworkAvailable()){
                repository.getBreedsFromDb()
                    .map {
                        it.data.toUIModel()
                    }.collect {
                        _breeds.value = it
                    }
                return@withContext
            }
            repository.getCatBreeds()
                .map {
                    when(it) {
                        is Async.Success -> it.data.toUIModel()
                        is Async.Error -> it
                        else -> Async.None
                    }
                }.collect {
                    _breeds.value = it
                }
        }
    }
}