package com.ci.myassignment.ui

import com.ci.myassignment.data.BaseRetrofit
import com.ci.myassignment.model.AlbumModels
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AlbumsPresenter(val albumsView: AlbumsView) {
    fun getTabs() {
        albumsView.startLoading()
        BaseRetrofit.apis?.getAlbums()?.enqueue(object : Callback<List<AlbumModels>> {
            override fun onFailure(call: Call<List<AlbumModels>>, t: Throwable) {
                albumsView.finishLoading()
                albumsView.showErrorMessage(t.message.toString())
            }

            override fun onResponse(
                call: Call<List<AlbumModels>>,
                response: Response<List<AlbumModels>>
            ) {
                if (response.isSuccessful) {
                    albumsView.showAlbum(response.body())
                    albumsView.finishLoading()
                } else {
                    albumsView.showErrorMessage("Response Failed")

                }

            }
        })
    }

}