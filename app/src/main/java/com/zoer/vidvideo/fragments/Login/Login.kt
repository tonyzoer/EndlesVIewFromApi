package com.zoer.vidvideo.fragments.Login

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast

import com.zoer.vidvideo.R
import com.zoer.vidvideo.features.VidMeUserManager
import com.zoer.vidvideo.fragments.BaseContainerFragment
import com.zoer.vidvideo.fragments.FeedTab.FeedVideosTab
import com.zoer.vidvideo.models.VidMeUserModel
import kotlinx.android.synthetic.main.fragment_login.*
import kotlinx.android.synthetic.main.fragment_login.view.*
import rx.schedulers.Schedulers

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [Login.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [Login.newInstance] factory method to
 * create an instance of this fragment.
 */
class Login : BaseContainerFragment(), View.OnClickListener {
    private var mListener: OnFragmentInteractionListener? = null
    private val userManager by lazy { VidMeUserManager() }
    private var userModel: VidMeUserModel? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val root = inflater!!.inflate(R.layout.fragment_login, container, false)
        root.login.setOnClickListener(this)
        return root
    }

    // TODO: Rename method, update argument and hook method into UI event
    fun onButtonPressed(uri: Uri) {
        if (mListener != null) {
            mListener!!.onFragmentInteraction(uri)
        }
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if (context is OnFragmentInteractionListener) {
            mListener = context
        } else {
            throw RuntimeException(context!!.toString() + " must implement OnFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        mListener = null
    }

    override fun onClick(p0: View) {
        when (p0.id) {
            R.id.login -> login()
        }
    }

    private fun login() {
        userManager.getUser(username.text.toString(), password.text.toString())
                .subscribeOn(Schedulers.io())
                .subscribe({ user ->
                    userModel = user
                    activity.getSharedPreferences("pref", Context.MODE_PRIVATE).edit().putString("token", user.token).apply()
                    Log.d(TAG, "token= ${user.token}")
                    replaceFragment(FeedVideosTab(),false)
                },
                        { err ->
                            Log.d(TAG, "user not logined")
                        })
    }

    interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        fun onFragmentInteraction(uri: Uri )
    }

    companion object {
        val TAG = this.javaClass.simpleName
        fun newInstance(): Login {
            val fragment = Login()
            return fragment
        }
    }
}
