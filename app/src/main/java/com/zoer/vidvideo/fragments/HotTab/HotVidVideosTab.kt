package com.zoer.vidvideo.fragments.HotTab

import com.zoer.vidvideo.commons.TabType
import com.zoer.vidvideo.fragments.VidVideoTabRecyclerViewFragment

/**
 * Created by mafio on 11/21/2017.
 */
class HotVidVideosTab : VidVideoTabRecyclerViewFragment() {
    override fun requestVideo(tabType: TabType,token:String) {
        super.requestVideo(TabType.HOT,token)
    }
    companion object {
        val TAG = "HotTab"


    }
}