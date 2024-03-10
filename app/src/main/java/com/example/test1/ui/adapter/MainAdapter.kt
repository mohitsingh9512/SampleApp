package com.example.test1.ui.adapter

import android.view.ViewGroup
import com.example.listcomponent.adapter.BasicAdapter
import com.example.listcomponent.viewholder.AbstractViewHolder
import com.example.test1.ui.viewholder.ui.CatBreedViewHolder
import com.example.listcomponent.datamodel.BaseDataModel
import com.example.test1.ui.viewholder.uimodel.CatBreedDataModel

/*
    Using Visitor Pattern
 */
class MainAdapter(private val listener : CatBreedInterface) : BasicAdapter(MainDiffUtilCallback(), listener) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AbstractViewHolder<*> {
        val view  = createViewItem(parent,viewType)
        return when(viewType) {
            CatBreedViewHolder.LAYOUT -> CatBreedViewHolder(view,listener)
            else -> super.onCreateViewHolder(parent, viewType)
        }
    }

    override fun onBindViewHolder(holder: AbstractViewHolder<*>, position: Int) {
        bind(holder as AbstractViewHolder<BaseDataModel>, position)
    }


    private fun bind(holder: AbstractViewHolder<BaseDataModel>, position: Int) {
        holder.bind(getItem(position))
    }

    override fun getItemViewType(position: Int): Int {
        return when(getItem(position)){
            is CatBreedDataModel -> CatBreedViewHolder.LAYOUT
            else -> super.getItemViewType(position)
        }
    }
}
