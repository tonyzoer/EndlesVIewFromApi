package com.zoer.vidvideo.fragments.FeedTab

import android.content.Context
import android.util.Log
import com.zoer.vidvideo.adapters.VidVideosAdapter
import com.zoer.vidvideo.commons.TabType
import com.zoer.vidvideo.fragments.Login.Login
import com.zoer.vidvideo.fragments.VidVideoTabRecyclerViewFragment
import kotlinx.android.synthetic.main.fragment_vid_video_recycler_view.*
import rx.schedulers.Schedulers

/**
 * Created by mafio on 11/24/2017.
 */
class FeedVideosTab:VidVideoTabRecyclerViewFragment() {
    override fun requestVideo(tabType: TabType,token:String) {
        val token= context.getSharedPreferences("pref", Context.MODE_PRIVATE).getString("token","token")
        videosManager.getVideos(vidVideos?.getAfter() ?: 0, tabtype = tabType, accessToken = token)
                .subscribeOn(Schedulers.io())
                .subscribe(
                        { retrivedVideos ->
                            vidVideos = retrivedVideos
                            (vid_video_recycler.adapter as VidVideosAdapter).addVideos(retrivedVideos.videos)
                        },
                        { e ->
                            Log.d(VidVideoTabRecyclerViewFragment.TAG, e.message)
                            replaceFragment(Login.newInstance(),false)
                        }
                )
    }
    companion object {
        val TAG = "FeedTab"


    }
}