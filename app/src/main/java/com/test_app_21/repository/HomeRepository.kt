package com.test_app_21.repository

import com.test_app_21.network.ApiInterface
import com.test_app_21.network.BaseRepository
import com.test_app_21.network.ResponseWrapper
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject


class HomeRepository
@Inject constructor(private val apiInterface: ApiInterface?) :
    BaseRepository() {


    // get photo list
    suspend fun getPhotos(): ResponseWrapper<Any?> {
        return baseApiCall(Dispatchers.IO) { apiInterface?.getPhotos()}
    }

}