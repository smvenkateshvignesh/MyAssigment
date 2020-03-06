package com.ci.myassignment.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.ci.myassignment.R
import com.ci.myassignment.model.AlbumModels
import com.ci.myassignment.ui.fragment.PhotosFragment
import com.ci.myassignment.utils.Network
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.activity_main.*

class AlbumsActivity : AppCompatActivity(),AlbumsView {

    companion object{
       val  BUNDLE_KEY_ALBUM = "BUNDLE_KEY_ALBUM"
    }

    private val manager = supportFragmentManager

    override fun startLoading() {
        progressBarLayout.visibility = View.VISIBLE
    }

    override fun finishLoading() {
        progressBarLayout.visibility = View.GONE
    }

    override fun showAlbum(list: List<AlbumModels>?) {
        list?.let {
            for(tabs in it){
                val newTab = tlMyTabLayout.newTab()
                newTab.text = tabs.title
                newTab.tag = tabs.id
                tlMyTabLayout.addTab(newTab)
            }
        }

    }

    override fun showErrorMessage(message: String) {
        Snackbar.make(root,message,Snackbar.LENGTH_SHORT).show()
    }

    private var albumsPresenter:AlbumsPresenter ?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        bindPresenter()
        initViews()
    }

    private fun bindPresenter() {
        albumsPresenter = AlbumsPresenter(this)
    }

    private fun initViews() {
        tlMyTabLayout.addOnTabSelectedListener(object :
            TabLayout.OnTabSelectedListener {
            override fun onTabReselected(tab: TabLayout.Tab?) {

            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabSelected(tab: TabLayout.Tab?) {
                loadFragment((tab?.tag?:1) as Int)
            }

        })
        if(Network.isNetworkConnected()){
            albumsPresenter?.getTabs()
        }else{
            showErrorMessage(getString(R.string.internet_connection_error))
        }

    }

    private fun loadFragment(albumId:Int) {
        val frag = PhotosFragment()
        val ft = manager.beginTransaction()
        ft.disallowAddToBackStack()
        val bundle = Bundle()
        bundle.putInt(BUNDLE_KEY_ALBUM,albumId)
        frag.arguments = bundle
        ft.replace(R.id.container, frag, frag.javaClass.simpleName)
        try {
            ft.commit()
        } catch (ex: Exception) {
            ex.printStackTrace()
        }
    }
}
