package com.example.listcomponent.datamodel

import com.example.listcomponent.datamodel.BaseDataModel

class EmptyDataModel(
    override val uniqueId: String = "${Integer.MAX_VALUE}",
    val message: String
) : BaseDataModel