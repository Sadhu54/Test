package com.test_app_21.model

import android.os.Bundle

data class BaseCallbackState(
    val isLoading:Boolean?=null,
    val success:Boolean?=null,
    val responseCode:Int?=null,
    val message: String? =null,
    var response:Any?= null,
)