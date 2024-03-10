package com.example.listcomponent.viewholder

import android.view.View
import androidx.recyclerview.widget.RecyclerView

abstract class AbstractViewHolder<in Model>(view : View) : RecyclerView.ViewHolder(view) {
    abstract fun bind(model: Model)
}