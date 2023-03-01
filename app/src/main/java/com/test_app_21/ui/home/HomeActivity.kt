package com.test_app_21.ui.home

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.test_app_21.databinding.ActivityHomeBinding
import com.test_app_21.model.PhotoListResponse
import com.test_app_21.ui.DetailActivity
import com.test_app_21.utils.EventObserver
import com.test_app_21.utils.RecyclerItemClickListener
import com.test_app_21.utils.ktx.visibilityGone
import com.test_app_21.utils.ktx.visibilityVisible
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {
    private lateinit var viewBinding: ActivityHomeBinding
    private val homeViewModel: HomeViewModel by viewModels()
    var photoList = ArrayList<PhotoListResponse>()

    lateinit var photoAdapter: PhotosAdapter


    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityHomeBinding.inflate(layoutInflater).apply {
            tvTitle.text =
                "FitPeo"
        }
        setContentView(viewBinding.root)
        setObserver()
    }


    // function to set observer for this class
    private fun setObserver() {
        viewBinding.apply {

            // observable for fetching film response
            homeViewModel.getFilmLive.observe(this@HomeActivity, EventObserver {
                it.apply {
                    when (isLoading) {
                        true -> {
                            shimmerHome.visibilityVisible()
                        }
                        false -> {
                            shimmerHome.visibilityGone()
                            llHome.visibilityVisible()
                        }
                        else -> {}
                    }
                    when (success) {
                        true -> {
                            (response as ArrayList<PhotoListResponse>).apply {


                                photoList = this.clone() as ArrayList<PhotoListResponse>
                                // setting up photo list
                                photoAdapter = PhotosAdapter(photoList)
                                rvMovies.apply {
                                    adapter = photoAdapter
                                    addOnItemTouchListener(RecyclerItemClickListener(this@HomeActivity,object :RecyclerItemClickListener.OnItemClickListener{
                                        override fun onItemClick(
                                            view: View,
                                            position: Int
                                        ) {
                                            startActivity(Intent(this@HomeActivity,DetailActivity::class.java).putExtra("data",photoList[position]))
                                        }
                                    }))
                                }


                            }
                        }
                        else -> {

                        }
                    }
                }
            })
        }
    }
}