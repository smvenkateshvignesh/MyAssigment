package com.ci.myassignment.ui.fragment


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager

import com.ci.myassignment.R
import com.ci.myassignment.model.PhotosModel
import com.ci.myassignment.ui.AlbumsActivity
import com.ci.myassignment.ui.AlbumsView
import com.ci.myassignment.ui.adapter.PhotosAdapter
import kotlinx.android.synthetic.main.fragment_photos.view.*

class PhotosFragment : Fragment(),PhotoView {
    override fun startLoading() {
        (activity as? AlbumsView)?.startLoading()
    }

    override fun finishLoading() {
        (activity as? AlbumsView)?.finishLoading()    }

    override fun showAlbum(list: List<PhotosModel>?) {
        list?.let {
            photosAdapter?.addCategory(list as ArrayList<PhotosModel>)
        }

    }

    override fun showErrorMessage(message: String) {
        (activity as? AlbumsView)?.showErrorMessage(message)    }

    private var photosPresenter:PhotosPresenter? = null
    private var photosAdapter:PhotosAdapter ? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_photos, container, false)
        bindPresenter()
        initViews(view)
        return view
    }

    private fun initViews(view: View) {
        view.recyclerViewPhotos.let {
            it.layoutManager = GridLayoutManager(context,3)
            photosAdapter = PhotosAdapter()
            it.adapter = photosAdapter
        }

        val albumId = arguments?.getInt(AlbumsActivity.BUNDLE_KEY_ALBUM)?:1
        photosPresenter?.getPhotos(albumId)
    }

    private fun bindPresenter() {
        photosPresenter = PhotosPresenter(this)
    }


}
