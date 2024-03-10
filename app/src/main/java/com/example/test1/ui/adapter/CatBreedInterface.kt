package com.example.test1.ui.adapter

import com.example.listcomponent.adapter.BasicAdapterListenerInterface
import com.example.test1.ui.viewholder.uimodel.CatBreedDataModel


interface CatBreedInterface : BasicAdapterListenerInterface {
    fun onBreedClick(catBreedDataModel: CatBreedDataModel)
}