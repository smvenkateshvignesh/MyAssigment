package com.ci.myassignment.model


import com.google.gson.annotations.SerializedName

data class AlbumModels(
    @SerializedName("id")
    val id: Int?,
    @SerializedName("title")
    val title: String?,
    @SerializedName("userId")
    val userId: Int?
)