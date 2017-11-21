package com.zoer.vidvideo.api

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

/**
 * Created by mafio on 11/21/2017.
 */
class RestApi {

    private val redditApi: VidVideoApi

    init {
        val retrofit = Retrofit.Builder()
                .baseUrl("https://api.vid.me")
                .addConverterFactory(MoshiConverterFactory.create())
                .build()

        redditApi = retrofit.create(VidVideoApi::class.java)
    }

    fun getHot(offset: String="", limit: String=""): Call<VidMeVideosResponse> {
        return redditApi.getTopHot(offset, limit)
    }

    fun getFeatured(offset: String="", limit: String=""): Call<VidMeVideosResponse> {
        return redditApi.getTopFeatured(offset, limit)
    }

    fun getFeed(offset: String="", limit: String="", accessToken: String = ""): Call<VidMeVideosResponse> {
        return redditApi.getTopFeed(offset, limit, accessToken)
    }
}