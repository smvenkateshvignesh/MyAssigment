package com.ci.myassignment.data


import com.ci.myassignment.model.AlbumModels
import com.ci.myassignment.model.PhotosModel
import retrofit2.Call
import retrofit2.http.*


interface API {
    @GET("albums")
    fun getAlbums(): Call<List<AlbumModels>>

    @GET("photos")
    fun getPhotos(@Query("albumId")albumId :Int): Call<List<PhotosModel>>

}