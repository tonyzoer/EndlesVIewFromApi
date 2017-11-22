package com.zoer.vidvideo.fragments.FeaturedTabFragment

import com.zoer.vidvideo.commons.TabType
import com.zoer.vidvideo.fragments.VidVideoTabRecyclerViewFragment


class FeaturedRecyclerViewFragment : VidVideoTabRecyclerViewFragment() {
    override fun requestVideo(tabType: TabType) {
    super.requestVideo(TabType.FEATURED)
    }
}
