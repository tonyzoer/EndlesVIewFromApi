package com.zoer.vidvideo.fragments.NewTabFragment

import android.util.Log
import com.zoer.vidvideo.api.RestApi
import com.zoer.vidvideo.models.VidVideoModel
import com.zoer.vidvideo.models.VidVideosModel
import rx.Observable

/**
 * Created by mafio on 11/21/2017.
 */
class FeaturedVidVideosManager(private val api: RestApi = RestApi()) {
    fun getFeatured(offset: Int = 0, limit: Int = 10): Observable<VidVideosModel> {
        return Observable.create { subscriber ->

            val callResponse = api.getFeatured(offset.toString(), limit.toString())
            val response = callResponse.execute()

            //TODO try to remove "?"
            if (response.isSuccessful) {
                Log.d("LUUUUL",response.body().toString())
                val dataResponse = response.body()!!
                val videos = dataResponse.videos.map {
                    VidVideoModel(it.video_id, it.title, it.thumbnail, it.url, it.score)
                }
                val vidVideos = VidVideosModel(dataResponse.page.offset, dataResponse.page.limit, videos)
                subscriber.onNext(vidVideos)
                subscriber.onCompleted()
            } else {
                subscriber.onError(Throwable(response.message()))
            }
        }
    }
}