package com.test_app_21.ui.home

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import com.test_app_21.databinding.LayoutPhotoCellBinding
import com.test_app_21.model.PhotoListResponse
import com.test_app_21.utils.ktx.visibilityGone
import com.test_app_21.utils.ktx.visibilityVisible


class PhotosAdapter(
    private val filmList: MutableList<PhotoListResponse>
) : RecyclerView.Adapter<PhotosAdapter.MovieViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {

        return MovieViewHolder(
            LayoutPhotoCellBinding.inflate(
                LayoutInflater.from(
                    parent.context
                ), parent, false
            )
        )
    }

    override fun getItemCount(): Int {
        return filmList.size
    }

    @SuppressLint("SetTextI18n", "NotifyDataSetChanged")
    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.viewBinding.apply {

            filmList[position].apply {
                tvMovieName.text = title


                lottieLoader.visibilityVisible()
                ivPhoto.apply {
                    Picasso.get().load(url).into(this, object : Callback.EmptyCallback() {
                        override fun onSuccess() {
                            lottieLoader.visibilityGone()
                        }
                    })


                }

            }
        }
    }

    inner class MovieViewHolder(val viewBinding: LayoutPhotoCellBinding) :
        RecyclerView.ViewHolder(viewBinding.root)
}