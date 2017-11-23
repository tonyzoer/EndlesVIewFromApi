package com.zoer.vidvideo.adapters

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter

import com.zoer.vidvideo.fragments.DummySectionTab.DummySection
import com.zoer.vidvideo.fragments.FeaturedTab.FeaturedVideosTab
import com.zoer.vidvideo.fragments.FeedTab.FeedVideosTab
import com.zoer.vidvideo.fragments.HotTab.HotVidVideosTab
import com.zoer.vidvideo.fragments.Login.Login

class AppSectionsPagerAdapter(fm: FragmentManager, val context: Context) : FragmentPagerAdapter(fm) {

    override fun getItem(i: Int): Fragment {
        return when (i) {
            0 -> FeaturedVideosTab()
            1 -> HotVidVideosTab()
            2 -> {val token=context.getSharedPreferences("pref", Context.MODE_PRIVATE).getString("token", "token")
                if (!token.equals("token")) {
                    FeedVideosTab()
                } else {
                    Login()
                }
            }
            else -> {
                // The other sections of the app are dummy placeholders.
                val fragment = DummySection()
                val args = Bundle()
                args.putInt(DummySection.ARG_SECTION_NUMBER, i + 1)
                fragment.arguments = args
                fragment
            }
        }
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
}