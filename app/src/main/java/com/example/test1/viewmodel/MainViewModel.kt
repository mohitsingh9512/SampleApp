package com.example.test1.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.listcomponent.network.Async
import com.example.listcomponent.datamodel.BaseDataModel
import com.example.test1.usecase.CatUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject


/*
    List all possible states of your respective UI Screen
 */
sealed class MainUiState {
    data class SuccessItems(val items: List<BaseDataModel> = emptyList()) : MainUiState()
    data class Error(val errorCode: Int? = null, val userMessage: String? = null) : MainUiState()
    data class Loading(val isLoading: Boolean) : MainUiState()
    data object None : MainUiState()
}

class MainViewModel @Inject constructor(private val catUseCase: CatUseCase): ViewModel() {

    private val _userMessage = MutableStateFlow(null)
    private val _isLoading = MutableStateFlow(false)
    private val _result =  catUseCase.breeds

    init {
        getBreeds()
    }

    val uiState: StateFlow<MainUiState> = combine(
         _isLoading, _userMessage, _result
    ) { loading, message, result ->
        when(result){
            Async.Loading -> {
                MainUiState.Loading(isLoading = true)
            }

            is Async.Error -> {
                MainUiState.Error(errorCode = result.errorCode)
            }

            is Async.Success -> {
                 MainUiState.SuccessItems(
                    items = result.data
                 )
            }

            is Async.None -> {
                MainUiState.None
            }
        }
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(1000),
                initialValue = MainUiState.Loading(isLoading = true)
    )

    fun getBreeds(){
        viewModelScope.launch(Dispatchers.IO) {
            catUseCase.getBreeds()
        }
    }
}