package com.assesment.fitpeoassignment.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.assesment.fitpeoassignment.R
import com.assesment.fitpeoassignment.model.PhotoDetail
import com.squareup.picasso.Picasso

/**
 * Recycler Adapter class for Photos List
 */
class PhotoListAdapter(val photoList: ArrayList<PhotoDetail>, val mListener: ClicListener) :
    RecyclerView.Adapter<PhotoListAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.list_layout, parent, false)
        return ViewHolder(v)
    }
    override fun getItemCount(): Int {
        return photoList.size
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItems(photoList[position])
    }
    interface ClicListener {
        fun onClick(photo: PhotoDetail)
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener {


        fun bindItems(photo: PhotoDetail) {
            val title = itemView.findViewById<TextView>(R.id.title)
            val img = itemView.findViewById<ImageView>(R.id.img)
            val llParent = itemView.findViewById<LinearLayout>(R.id.llParent)
            title.text = photo.title

            Picasso.get().load(photo.thumbnailUrl)
                .placeholder(R.drawable.ic_launcher_background)
                .into(img)

            llParent.setOnClickListener(this)
        }
        override fun onClick(view: View?) {
            when (view?.id) {
                R.id.llParent -> {
                    mListener.onClick(photoList[absoluteAdapterPosition])
                }
            }

        }

    }
}