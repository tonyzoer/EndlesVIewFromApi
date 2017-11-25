package com.zoer.vidvideo.fragments

import android.support.v4.app.Fragment
import android.util.Log
import com.zoer.vidvideo.R

/**
 * Created by mafio on 11/24/2017.
 */
open class BaseContainerFragment: Fragment() {
    fun replaceFragment(fragment: Fragment, addToBackStack: Boolean) {
        val transaction = fragmentManager.beginTransaction()
        if (addToBackStack) {
            transaction.addToBackStack(null)
        }
        transaction.replace(R.id.root_frame, fragment)
        transaction.commit()
        childFragmentManager.executePendingTransactions()
    }

    private val TAG: String = "BaseActivity"

    fun popFragment(): Boolean {
        Log.e(TAG, "pop fragment: " + childFragmentManager.backStackEntryCount)
        var isPop = false
        if (childFragmentManager.backStackEntryCount > 0) {
            isPop = true
            childFragmentManager.popBackStack()
        }
        return isPop
    }
}