package com.example.test1.ui.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.listcomponent.datamodel.BaseDataModel

class MainDiffUtilCallback: DiffUtil.ItemCallback<BaseDataModel>() {
    override fun areItemsTheSame(oldItem: BaseDataModel, newItem: BaseDataModel): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: BaseDataModel, newItem: BaseDataModel): Boolean {
        return oldItem.uniqueId == newItem.uniqueId
    }
}