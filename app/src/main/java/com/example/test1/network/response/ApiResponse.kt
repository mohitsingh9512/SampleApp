package com.example.test1.network.response


import androidx.room.Entity
import androidx.room.PrimaryKey
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
    val lifeSpan: String? = ""
)

data class CatBreedDetailModel(
    @SerializedName("id")
    val id: String? = "",
    @SerializedName("url")
    val url: String? = ""
)