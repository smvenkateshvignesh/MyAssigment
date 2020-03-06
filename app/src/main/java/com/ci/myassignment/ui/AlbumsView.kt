package com.ci.myassignment.ui

import com.ci.myassignment.model.AlbumModels

interface AlbumsView {
    fun startLoading()
    fun finishLoading()
    fun showAlbum(list: List<AlbumModels>?)
    fun showErrorMessage(message: String)
}