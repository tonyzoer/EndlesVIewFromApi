package com.zoer.vidvideo.features

import com.zoer.vidvideo.api.RestApi
import com.zoer.vidvideo.commons.TabType
import com.zoer.vidvideo.models.VidVideoModel
import com.zoer.vidvideo.models.VidVideosModel
import rx.Observable

/**
 * Created by mafio on 11/21/2017.
 */
class VidMeVideosManager(private val api: RestApi = RestApi()) {
    private val TAG: String = this.javaClass.name

    fun getVideos(offset: Int = 0, limit: Int = 10, tabtype: TabType = TabType.FEATURED, accessToken: String = ""): Observable<VidVideosModel> {
        return Observable.create { subscriber ->

            val callResponse = when (tabtype) {
                TabType.FEED -> api.getFeed(offset.toString(), limit.toString(), accessToken)
                TabType.HOT -> api.getHot(offset.toString(), limit.toString())
                TabType.FEATURED -> api.getFeatured(offset.toString(), limit.toString(),accessToken)
            }
            val response = callResponse.execute()

            //TODO try to remove "?"
            if (response.isSuccessful) {
                val dataResponse = response.body()!!
                val videos = dataResponse.videos.map {
                    VidVideoModel(it.video_id, it.title, it.thumbnail, it.complete_url, it.score)
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