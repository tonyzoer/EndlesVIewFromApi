package com.zoer.vidvideo.adapters

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter

import com.zoer.vidvideo.fragments.DummySectionTabFragment.DummySectionFragment
import com.zoer.vidvideo.fragments.FeaturedTabFragment.FeaturedRecyclerViewFragment
import com.zoer.vidvideo.fragments.HotTabFragment.HotVidVideosTabFragment

class AppSectionsPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

    override fun getItem(i: Int): Fragment {
        return when (i) {
            0 -> FeaturedRecyclerViewFragment()
            1 -> HotVidVideosTabFragment()
            else -> {
                // The other sections of the app are dummy placeholders.
                val fragment = DummySectionFragment()
                val args = Bundle()
                args.putInt(DummySectionFragment.ARG_SECTION_NUMBER, i + 1)
                fragment.arguments = args
                fragment
            }
        }
    }

    override fun getCount(): Int {
        return 3
    }

    override fun getPageTitle(position: Int): CharSequence {
        return when(position){
            0->"Featured"
            1->"Hot"
            2->"Feed"
            else -> {"NEW ONE"
            }
        }
    }
}