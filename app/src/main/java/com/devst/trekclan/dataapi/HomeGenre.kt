package com.devst.trekclan.dataapi

import com.devst.trekclan.dataapi.GenreDataClasses.GenereDataParent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET


const val BASE_URL="https://ws.audioscrobbler.com/"
const val API_KEY="db04f23a1f0d3f32f124631af1624cfe"
interface Tracks {

    @GET("2.0/?method=track.gettoptags&artist=radiohead&track=paranoid+android&api_key=$API_KEY&format=json")
   fun getTopTags():retrofit2.Call<GenereDataParent>

}

object HomeGenre{
    val tracksInstance:Tracks
    init {
        val retrofit= Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        tracksInstance=retrofit.create(Tracks::class.java)
    }
}