package com.zoer.vidvideo.adapters

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.view.PagerAdapter
import android.view.ViewGroup

import com.zoer.vidvideo.fragments.DummySectionTab.DummySection
import com.zoer.vidvideo.fragments.FeaturedTab.FeaturedVideosTab
import com.zoer.vidvideo.fragments.FeedTab.FeedVideosTab
import com.zoer.vidvideo.fragments.HotTab.HotVidVideosTab
import com.zoer.vidvideo.fragments.Login.Login
import com.zoer.vidvideo.fragments.Login.LoginRootFragment

class AppSectionsPagerAdapter(fm: FragmentManager, val context: Context) : FragmentPagerAdapter(fm) {

    var loginRoot:LoginRootFragment?=null
    override fun getItem(i: Int): Fragment {
        return when (i) {
            0 -> FeaturedVideosTab()
            1 -> HotVidVideosTab()
            2 -> LoginRootFragment()
            else -> {
                val fragment = DummySection()
                val args = Bundle()
                args.putInt(DummySection.ARG_SECTION_NUMBER, i + 1)
                fragment.arguments = args
                fragment
            }
        }
    }

    override fun instantiateItem(container: ViewGroup?, position: Int): Any {
        val createdFragment= super.instantiateItem(container, position)
        when(position){
            2->loginRoot=createdFragment as LoginRootFragment
        }
        return createdFragment
    }

    override fun getCount(): Int {
        return 3
    }

    override fun getPageTitle(position: Int): CharSequence {
        return when (position) {
            0 -> "Featured"
            1 -> "Hot"
            2 -> "Feed"
            else -> {
                "NEW ONE"
            }
        }
    }

    override fun getItemPosition(`object`: Any?): Int {
        return when (`object`) {
            is FeaturedVideosTab -> 0
            is HotVidVideosTab -> 1
            is FeedVideosTab, is Login, is LoginRootFragment -> 2
            else -> {
                PagerAdapter.POSITION_NONE
            }
        }

    }

    fun onLogOut() {
    loginRoot?.onLogOut()
    }

    interface LoginTabFragmentListener {
        fun onSwitchToFeedTab()
    }
}