package com.zoer.vidvideo.models

/**
 * Created by mafio on 11/21/2017.
 */
data class VidVideosModel(val offset: Int, val limit: Int, val videos: List<VidVideoModel>){
    fun getAfter(): Int {
        return offset+limit
    }
}
