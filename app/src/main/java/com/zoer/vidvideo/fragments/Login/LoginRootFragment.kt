package com.zoer.vidvideo.fragments.Login

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.zoer.vidvideo.R
import com.zoer.vidvideo.fragments.FeedTab.FeedVideosTab

class LoginRootFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val root = inflater!!.inflate(R.layout.fragment_root, container, false)
        val token = context.getSharedPreferences("pref", Context.MODE_PRIVATE).getString("token", "token")
        val transaction = fragmentManager.beginTransaction()

        if (token.equals("token"))
            transaction.replace(R.id.root_frame, Login.newInstance())
        else
            transaction.replace(R.id.root_frame, FeedVideosTab())

        transaction.commit()
        return root
    }


    companion object {
        fun newInstance(): LoginRootFragment {
            val fragment = LoginRootFragment()
            return fragment
        }
    }
}
