package com.example.test1.ui.viewholder.ui

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.listcomponent.viewholder.AbstractViewHolder
import com.example.test1.R
import com.example.test1.network.api.API_CAT_IMAGE
import com.example.test1.ui.adapter.CatBreedInterface
import com.example.test1.ui.viewholder.uimodel.CatBreedDataModel

class CatBreedViewHolder(view: View, private val listener : CatBreedInterface) : AbstractViewHolder<CatBreedDataModel>(view){

    companion object {
        val LAYOUT = R.layout.item_view_cat_breed
    }

    private val name : TextView by lazy {
        view.findViewById(R.id.tvCatBreedName)
    }
    private val image : ImageView by lazy {
        view.findViewById(R.id.image)
    }

    override fun bind(model: CatBreedDataModel) {
        name.text = model.catBreed.name
        Glide.with(itemView.context).load("$API_CAT_IMAGE${model.catBreed.reference_image_id}.jpg").into(image)
    }
}