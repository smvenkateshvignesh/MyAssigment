package com.ci.myassignment.ui.fragment

import com.ci.myassignment.model.PhotosModel

interface PhotoView {

    fun startLoading()
    fun finishLoading()
    fun showAlbum(list: List<PhotosModel>?)
    fun showErrorMessage(message: String)
}