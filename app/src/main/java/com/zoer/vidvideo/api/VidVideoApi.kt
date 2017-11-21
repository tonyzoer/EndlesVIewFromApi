package com.zoer.vidvideo.api

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by mafio on 11/21/2017.
 */
interface VidVideoApi {
    @GET("/videos/featured")
    fun getTopFeatured(@Query("offset") offset: String, @Query("limit") limit: String): Call<VidMeVideosResponse>

    @GET("/videos/hot")
    fun getTopHot(@Query("offset") offset: String, @Query("limit") limit: String): Call<VidMeVideosResponse>

    @GET("/videos/feed")
    fun getTopFeed(@Query("offset") offset: String, @Query("limit") limit: String, @Query("AccessToken") accessToken: String = ""): Call<VidMeVideosResponse>
}