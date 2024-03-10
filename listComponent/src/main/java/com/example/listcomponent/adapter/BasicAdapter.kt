package com.example.listcomponent.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.listcomponent.datamodel.BaseDataModel
import com.example.listcomponent.datamodel.BasicListDataModel
import com.example.listcomponent.viewholder.AbstractViewHolder
import com.example.listcomponent.viewholder.BasicListViewHolder
import com.example.listcomponent.viewholder.EmptyViewHolder

abstract class BasicAdapter(diffUtilCallback: DiffUtil.ItemCallback<BaseDataModel>,
                            private val listener: BasicAdapterListenerInterface? = null
) : ListAdapter<BaseDataModel, AbstractViewHolder<*>>(diffUtilCallback) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AbstractViewHolder<*> {
        val view  = createViewItem(parent,viewType)
        return when(viewType) {
            BasicListViewHolder.LAYOUT -> BasicListViewHolder(view,listener)
            else -> EmptyViewHolder(view)
        }
    }

    fun createViewItem(parent: ViewGroup, viewType: Int): View {
        return LayoutInflater.from(parent.context).inflate(viewType,parent,false)
    }

    override fun getItemViewType(position: Int): Int {
        return when(getItem(position)){
            is BasicListDataModel -> BasicListViewHolder.LAYOUT
            else -> EmptyViewHolder.LAYOUT
        }
    }
}