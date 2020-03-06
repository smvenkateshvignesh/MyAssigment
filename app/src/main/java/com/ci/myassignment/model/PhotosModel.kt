package com.ci.myassignment.model


import com.google.gson.annotations.SerializedName

data class PhotosModel(
    @SerializedName("albumId")
    val albumId: Int?,
    @SerializedName("id")
    val id: Int?,
    @SerializedName("thumbnailUrl")
    var thumbnailUrl: String?,
    @SerializedName("title")
    val title: String?,
    @SerializedName("url")
    val url: String?
)