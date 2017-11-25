package com.zoer.vidvideo.api

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

/**
 * Created by mafio on 11/21/2017.
 */
class RestApi {

    private val VidMeApi: VidVideoApi

    init {
        val retrofit = Retrofit.Builder()
                .baseUrl("https://api.vid.me")
                .addConverterFactory(MoshiConverterFactory.create())
                .build()

        VidMeApi = retrofit.create(VidVideoApi::class.java)
    }

    fun getHot(offset: String = "", limit: String = ""): Call<VidMeVideosResponse> {
        return VidMeApi.getTopHot(offset, limit)
    }

    fun getFeatured(offset: String = "", limit: String = "",accessToken: String = ""): Call<VidMeVideosResponse> {
        return VidMeApi.getTopFeatured(offset, limit,accessToken)
    }

    fun getFeed(offset: String = "", limit: String = "", accessToken: String = ""): Call<VidMeVideosResponse> {
        return VidMeApi.getTopFeed(offset, limit, accessToken)
    }

    fun getApp(auth: String): Call<VidMeAplicationResponse> {
        return VidMeApi.authcheck(auth)
    }

    fun getUser(username: String, password: String): Call<VidMeUserResponse> {
        return VidMeApi.authCreate(username, password)
    }
}