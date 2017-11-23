package com.zoer.vidvideo.fragments.FeedTab

import android.content.Context
import com.zoer.vidvideo.commons.TabType
import com.zoer.vidvideo.fragments.VidVideoTabRecyclerViewFragment

/**
 * Created by mafio on 11/24/2017.
 */
class FeedVideosTab:VidVideoTabRecyclerViewFragment() {
    override fun requestVideo(tabType: TabType,token:String) {
        context.getSharedPreferences("pref", Context.MODE_PRIVATE).getString("token","token")
        super.requestVideo(TabType.FEED,context.getSharedPreferences("pref", Context.MODE_PRIVATE).getString("token","token"))
    }
    companion object {
        val TAG = "FeedTab"


    }
}