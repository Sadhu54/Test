package com.test_app_21.network

import com.test_app_21.model.PhotoListResponse
import com.test_app_21.utils.TestConstants
import retrofit2.Response
import retrofit2.http.*


interface ApiInterface {


    //get photo list
    @GET(TestConstants.Endpoints.photoList)
    suspend fun getPhotos(): Response<ArrayList<PhotoListResponse>>

  }