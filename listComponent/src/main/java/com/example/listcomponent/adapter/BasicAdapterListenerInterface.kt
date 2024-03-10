package com.example.listcomponent.adapter

import com.example.listcomponent.datamodel.BaseDataModel


interface BasicAdapterListenerInterface {
    fun onClick(model: BaseDataModel) {}
}