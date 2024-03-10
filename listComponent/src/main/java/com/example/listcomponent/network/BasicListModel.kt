package com.example.listcomponent.network

import com.google.gson.annotations.SerializedName

data class BasicListModel(
    @SerializedName("id")
    val id: String = "",
    @SerializedName("name")
    val name: String? = "",
    @SerializedName("description")
    val description: String? = ""
)