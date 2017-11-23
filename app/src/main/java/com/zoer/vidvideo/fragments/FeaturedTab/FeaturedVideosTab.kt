package com.zoer.vidvideo.fragments.FeaturedTab

import com.zoer.vidvideo.commons.TabType
import com.zoer.vidvideo.fragments.VidVideoTabRecyclerViewFragment


class FeaturedVideosTab : VidVideoTabRecyclerViewFragment() {
    override fun requestVideo(tabType: TabType,token:String) {
    super.requestVideo(TabType.FEATURED,token)
    }
    companion object {
        val TAG = "Featured"


    }
}
