package com.example.test1.ui.viewholder.uimodel

import com.example.listcomponent.datamodel.BaseDataModel
import com.example.listcomponent.network.Async
import com.example.test1.network.response.CatBreedModel

class CatBreedDataModel(
    override val uniqueId: String,
    val catBreed: CatBreedModel
) : BaseDataModel

fun List<CatBreedModel>.toUIModel(): Async<List<CatBreedDataModel>> {
    val arrayListDataModel = arrayListOf<CatBreedDataModel>()
    for (catBreed in  this){
        arrayListDataModel.add(CatBreedDataModel(catBreed.id, catBreed))
    }
    return Async.Success(arrayListDataModel)
}