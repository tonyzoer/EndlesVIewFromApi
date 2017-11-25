package com.zoer.vidvideo.fragments

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.zoer.vidvideo.R
import com.zoer.vidvideo.activities.VideoActivity
import com.zoer.vidvideo.adapters.VidVideosAdapter
import com.zoer.vidvideo.commons.TabType
import com.zoer.vidvideo.commons.extensions.inflate
import com.zoer.vidvideo.features.VidMeVideosManager
import com.zoer.vidvideo.features.listeners.InfiniteScrollListener
import com.zoer.vidvideo.features.listeners.RecyclerItemClickListener
import com.zoer.vidvideo.models.VidVideosModel
import kotlinx.android.synthetic.main.fragment_vid_video_recycler_view.*
import rx.schedulers.Schedulers

open class VidVideoTabRecyclerViewFragment : BaseContainerFragment() {
    protected val videosManager by lazy { VidMeVideosManager() }
    protected var vidVideos: VidVideosModel? = null  //IMPORTANT

    private var mListener: OnFragmentInteractionListener? = null

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        Log.d(TAG, "Activty created")

        vid_video_recycler.setHasFixedSize(true)
        vid_video_recycler.layoutManager = LinearLayoutManager(context)
        vid_video_recycler.clearOnScrollListeners()
        vid_video_recycler.addOnScrollListener(InfiniteScrollListener({ requestVideo() }, vid_video_recycler.layoutManager as LinearLayoutManager))
        vid_video_recycler.addOnItemTouchListener(RecyclerItemClickListener(
                activity,
                vid_video_recycler,
                object : RecyclerItemClickListener.OnItemClickListener {
                    override fun onItemClick(view: View?, position: Int) {
                        Log.d(TAG, (vid_video_recycler.adapter as VidVideosAdapter).getVideos()[position].videoUrl)
                        startActivity(Intent(activity, VideoActivity::class.java).putExtra("complete_url", (vid_video_recycler.adapter as VidVideosAdapter).getVideos()[position].videoUrl))
                    }

                    override fun onLongItemClick(view: View?, position: Int) {
                        Toast.makeText(context, (vid_video_recycler.adapter as VidVideosAdapter).getVideos()[position].videoUrl, Toast.LENGTH_LONG).show()
                    }

                }
        ))
        initAdapter()
        if (savedInstanceState == null) {
            requestVideo()
        }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        Log.d(TAG, "View created")

        return container?.inflate(R.layout.fragment_vid_video_recycler_view)
    }

    fun onButtonPressed(uri: Uri) {
        if (mListener != null) {
            mListener!!.onFragmentInteraction(uri)
        }
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)

        Log.d(TAG, "Attached")

        if (context is OnFragmentInteractionListener) {
            mListener = context
        } else {
            throw RuntimeException((context!!.toString() + " must implement OnFragmentInteractionListener"))
        }
    }

    override fun onDetach() {
        super.onDetach()
        mListener = null
        Log.d(TAG, "Deatached")
    }

    protected open fun requestVideo(tabType: TabType = TabType.FEATURED, token: String = "token") {
        videosManager.getVideos(vidVideos?.getAfter() ?: 0, tabtype = tabType, accessToken = token)
                .subscribeOn(Schedulers.io())
                .subscribe(
                        { retrivedVideos ->
                            vidVideos = retrivedVideos
                            (vid_video_recycler.adapter as VidVideosAdapter).addVideos(retrivedVideos.videos)
                        },
                        { e ->
                            Log.d(TAG, e.message)
                        }
                )
    }

    private fun initAdapter() {
        if (vid_video_recycler.adapter == null) {
            vid_video_recycler.adapter = VidVideosAdapter()
        }
    }

    interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        fun onFragmentInteraction(uri: Uri)
    }

    companion object {
        val TAG = "VidVideoTab"


    }
}