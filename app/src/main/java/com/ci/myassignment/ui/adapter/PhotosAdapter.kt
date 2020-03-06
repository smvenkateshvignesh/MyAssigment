package com.ci.myassignment.ui.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.ci.myassignment.R
import com.ci.myassignment.model.PhotosModel
import com.squareup.picasso.Picasso

class PhotosAdapter() : RecyclerView.Adapter<PhotosAdapter.PhotosViewHolder>() {
    var photosList: ArrayList<PhotosModel> = ArrayList()



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotosViewHolder {
        return PhotosViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.inflate_photos_item,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return photosList.size
    }

    override fun onBindViewHolder(holder: PhotosViewHolder, position: Int) {
        holder.onBind(photosList[holder.adapterPosition])
    }


    inner class PhotosViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val imgPhoto: ImageView = itemView.findViewById(R.id.imgPhoto)
        private val tvTitle: TextView = itemView.findViewById(R.id.tvTitle)
        fun onBind(photoModel: PhotosModel) {
            tvTitle.text = photoModel.title
//            Log.e("URL",photoModel.thumbnailUrl)
//            Glide.with(imgPhoto.context)
//                .load(photoModel.thumbnailUrl?.trim())
//                .placeholder(getImagePlaceHolderLoading(imgPhoto.context))
//                .error(R.drawable.ic_placeholder)
//                .into(imgPhoto)

            Picasso.get().load(photoModel.thumbnailUrl).into(imgPhoto)
        }

    }

    fun addCategory(photosList: ArrayList<PhotosModel>) {
        clearList()
        this.photosList.addAll(photosList)
        notifyDataSetChanged()
    }

    private fun clearList() {
        photosList.clear()
        notifyDataSetChanged()
    }

    fun getImagePlaceHolderLoading(context: Context): CircularProgressDrawable {
        val circularProgressDrawable = CircularProgressDrawable(context)
        circularProgressDrawable.strokeWidth = 5f
        circularProgressDrawable.centerRadius = 30f
        circularProgressDrawable.start()
        return circularProgressDrawable
    }

}