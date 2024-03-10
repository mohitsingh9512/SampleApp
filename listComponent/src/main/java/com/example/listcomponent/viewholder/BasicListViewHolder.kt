package com.example.listcomponent.viewholder

import android.view.View
import android.widget.TextView
import com.example.listcomponent.R
import com.example.listcomponent.adapter.BasicAdapterListenerInterface
import com.example.listcomponent.datamodel.BasicListDataModel

class BasicListViewHolder(view: View, private val listener : BasicAdapterListenerInterface? = null) : AbstractViewHolder<BasicListDataModel>(view){

    companion object {
        val LAYOUT = R.layout.item_view_basic_list
    }

    private val name : TextView by lazy {
        view.findViewById(R.id.tvName)
    }

    override fun bind(model: BasicListDataModel) {
        name.text = model.model.name
        name.setOnClickListener {
            listener?.onClick(model)
        }
    }
}