package com.test_app_21.ui

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import com.test_app_21.R
import com.test_app_21.databinding.ActivityDetailBinding
import com.test_app_21.model.PhotoListResponse
import com.test_app_21.utils.ktx.visibilityGone

class DetailActivity : AppCompatActivity() {
    lateinit var viewBinding: ActivityDetailBinding
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding=ActivityDetailBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)
        if (intent.hasExtra("data"))
        {
            (intent.getSerializableExtra("data") as PhotoListResponse).apply {
                viewBinding.apply {
                    Picasso.get().load(url).into(ivThumbnail)
                    tvTitleId.text=title
                    tvAlbumId.text="Album ID: $albumId"
                    tvId.text="ID: $id"
                }
            }
        }
    }
}