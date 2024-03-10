package com.example.test1.network.response


import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.example.test1.network.api.API_CAT_IMAGE
import com.google.gson.annotations.SerializedName

@Entity
data class CatBreedModel(
    @PrimaryKey
    @SerializedName("id")
    val id: String = "",
    @SerializedName("name")
    val name: String? = "",
    @SerializedName("description")
    val description: String? = "",
    @SerializedName("origin")
    val origin: String? = "",
    @SerializedName("life_span")
    val lifeSpan: String? = "",
    @SerializedName("reference_image_id")
    val reference_image_id: String? = "",
)