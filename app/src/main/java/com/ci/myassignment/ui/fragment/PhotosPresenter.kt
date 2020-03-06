package com.ci.myassignment.ui.fragment

import com.ci.myassignment.data.BaseRetrofit
import com.ci.myassignment.model.PhotosModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PhotosPresenter(private val photoView: PhotoView) {

    fun getPhotos(albumId: Int) {
        photoView.startLoading()
        BaseRetrofit.apis?.getPhotos(albumId)?.enqueue(object : Callback<List<PhotosModel>> {
            override fun onFailure(call: Call<List<PhotosModel>>, t: Throwable) {
                photoView.finishLoading()
                photoView.showErrorMessage(t.message.toString())
            }

            override fun onResponse(
                call: Call<List<PhotosModel>>,
                response: Response<List<PhotosModel>>
            ) {
                if (response.isSuccessful) {
                    photoView.showAlbum(response.body())
                    photoView.finishLoading()
                } else {
                    photoView.showErrorMessage("Response Failed")

                }

            }
        })
    }
}