package com.zoer.vidvideo.fragments.HotTabFragment

import com.zoer.vidvideo.commons.TabType
import com.zoer.vidvideo.fragments.VidVideoTabRecyclerViewFragment

/**
 * Created by mafio on 11/21/2017.
 */
class HotVidVideosTabFragment : VidVideoTabRecyclerViewFragment() {
    override fun requestVideo(tabType: TabType) {
        super.requestVideo(TabType.HOT)
    }
}