package com.example.listcomponent.viewholder

import android.view.View
import com.example.listcomponent.R
import com.example.listcomponent.datamodel.EmptyDataModel

class EmptyViewHolder(view: View) : AbstractViewHolder<EmptyDataModel>(view){

    companion object {
        val LAYOUT = R.layout.item_view_empty
    }

    override fun bind(model: EmptyDataModel) {

    }
}