package com.test_app_21.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.test_app_21.model.BaseCallbackState
import com.test_app_21.model.PhotoListResponse
import com.test_app_21.network.ResponseWrapper
import com.test_app_21.repository.HomeRepository
import com.test_app_21.utils.Event
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.json.JSONObject
import retrofit2.Response
import javax.inject.Inject


@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: HomeRepository
) : ViewModel() {

    init {
        getPhotos()
    }

    // live data for photos
    private var getPhotoMutable = MutableLiveData<Event<BaseCallbackState>>()
    val getFilmLive: LiveData<Event<BaseCallbackState>> get() = getPhotoMutable

    // function for fetching film
    private fun getPhotos() {
        viewModelScope.launch(Dispatchers.IO) {
            getPhotoMutable.postValue(
                Event(
                    BaseCallbackState(
                        isLoading = true,
                    )
                )
            )
            when (val serverResponse = repository.getPhotos()) {
                is ResponseWrapper.Success<*> -> {
                    (serverResponse.value as Response<ArrayList<PhotoListResponse>>).apply {
                        when (code()) {
                            200 -> {
                                body().apply {
                                    getPhotoMutable.postValue(
                                        Event(
                                            BaseCallbackState(
                                                isLoading = false,
                                                success = true,
                                                response = this
                                            )
                                        )
                                    )
                                }
                            }

                            else -> {
                                var message = ""
                                message = try {
                                    val jObjError = JSONObject(
                                        serverResponse.value.errorBody()!!.string()
                                    )
                                    jObjError.getString("error")
                                } catch (e: Exception) {
                                    e.message.toString()
                                }
                                getPhotoMutable.postValue(
                                    Event(
                                        BaseCallbackState(
                                            isLoading = false,
                                            success = false,
                                            message = message
                                        )
                                    )
                                )
                            }
                        }
                    }
                }
                is ResponseWrapper.Error<*> -> {
                    getPhotoMutable.postValue(
                        Event(
                            BaseCallbackState(
                                isLoading = false,
                                success = false,
                                message = serverResponse.value.toString()
                            )
                        )
                    )
                }
            }
        }
    }

}