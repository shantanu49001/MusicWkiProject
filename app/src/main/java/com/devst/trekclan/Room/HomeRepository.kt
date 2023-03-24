package com.devst.trekclan.Room

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.devst.trekclan.dataapi.GenreDataClasses.GenereDataParent
import com.devst.trekclan.dataapi.HomeGenre
import com.devst.trekclan.dataapi.Tracks
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class HomeRepository(private val tagService: Tracks) {


    private val quotesLiveData = MutableLiveData<GenereDataParent>()
    val quotes: LiveData<GenereDataParent>
        get() = quotesLiveData

    suspend fun getTags() {


        val tracksRepo = HomeGenre.tracksInstance.getTopTags()
        tracksRepo.enqueue(object : Callback<GenereDataParent> {
            override fun onResponse(
                call: Call<GenereDataParent>,
                response: Response<GenereDataParent>
            ) {

                if (response?.body() != null) {
                    quotesLiveData.postValue(response.body())
                }
            }

            override fun onFailure(call: Call<GenereDataParent>, t: Throwable) {

            }

        })


    }
}