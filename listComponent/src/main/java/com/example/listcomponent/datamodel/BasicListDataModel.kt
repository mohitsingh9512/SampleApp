package com.example.listcomponent.datamodel

import com.example.listcomponent.network.Async
import com.example.listcomponent.network.BasicListModel


class BasicListDataModel(
    override val uniqueId: String,
    val model: BasicListModel
) : BaseDataModel

fun List<BasicListModel>.toUIModel(): Async<List<BasicListDataModel>> {
    val arrayListDataModel = arrayListOf<BasicListDataModel>()
    for (model in  this){
        arrayListDataModel.add(BasicListDataModel(model.id, model))
    }
    return Async.Success(arrayListDataModel)
}