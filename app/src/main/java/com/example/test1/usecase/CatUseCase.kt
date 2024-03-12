package com.example.test1.usecase

import android.content.Context
import com.example.listcomponent.network.Async
import com.example.test1.di.scope.ApplicationScope
import com.example.test1.di.scope.DefaultDispatcher
import com.example.test1.extensions.AppUtils
import com.example.test1.repo.CatsRepository
import com.example.test1.ui.viewholder.uimodel.CatBreedDataModel
import com.example.test1.ui.viewholder.uimodel.toUIModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class CatUseCase @Inject constructor(
    @ApplicationScope private val context: Context,
    private val repository: CatsRepository,
    @DefaultDispatcher private val dispatcher: CoroutineDispatcher,
    ) {

    fun getBreeds() : Flow<Async<List<CatBreedDataModel>>> = flow {
        if (!AppUtils.isNetworkAvailable(context)) {
            repository.getBreedsFromDb()
                .map {
                    it.data.toUIModel()
                }
                .flowOn(dispatcher)
                .collect {
                    emit(it)
                }
            return@flow
        }
        repository.getCatBreeds()
            .map {
                when (it) {
                    is Async.Success -> it.data.toUIModel()
                    is Async.Error -> it
                    else -> Async.None
                }
            }
            .flowOn(dispatcher)
            .collect {
                emit(it)
            }
    }
}