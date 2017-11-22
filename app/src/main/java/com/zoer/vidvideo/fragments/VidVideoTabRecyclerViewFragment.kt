package com.zoer.vidvideo.fragments

import android.content.Context
import android.net.Uri
import android.nfc.Tag
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.zoer.vidvideo.R
import com.zoer.vidvideo.adapters.VidVideosAdapter
import com.zoer.vidvideo.commons.TabType
import com.zoer.vidvideo.commons.extensions.inflate
import com.zoer.vidvideo.features.InfiniteScrollListener
import com.zoer.vidvideo.models.VidVideosModel
import kotlinx.android.synthetic.main.fragment_vid_video_recycler_view.*
import rx.schedulers.Schedulers

open class VidVideoTabRecyclerViewFragment : Fragment() {
    protected val videosManager by lazy { VidVideosManager() }
    protected var vidVideos: VidVideosModel? = null  //IMPORTANT

    private var mListener: OnFragmentInteractionListener? = null

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        Log.d(TAG,"Activty created")

        vid_video_recycler.setHasFixedSize(true)
        vid_video_recycler.layoutManager = LinearLayoutManager(context)
        vid_video_recycler.clearOnScrollListeners()
        vid_video_recycler.addOnScrollListener(InfiniteScrollListener({ requestVideo() }, vid_video_recycler.layoutManager as LinearLayoutManager))
        initAdapter()
        if (savedInstanceState == null) {
            requestVideo()
        }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        Log.d(TAG,"View created")

        return container?.inflate(R.layout.fragment_vid_video_recycler_view)
    }

    fun onButtonPressed(uri: Uri) {
        if (mListener != null) {
            mListener!!.onFragmentInteraction(uri)
        }
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)

        Log.d(TAG,"Attached")

        if (context is OnFragmentInteractionListener) {
            mListener = context as OnFragmentInteractionListener?
        } else {
            throw RuntimeException((context!!.toString() + " must implement OnFragmentInteractionListener")) as Throwable
        }
    }

    override fun onDetach() {
        super.onDetach()
        mListener = null

        Log.d(TAG,"Deatached")
    }

    protected open fun requestVideo(tabType: TabType = TabType.FEATURED) {
        videosManager.getVideos(vidVideos?.getAfter() ?: 0, tabtype = tabType)
                .subscribeOn(Schedulers.io())
                .subscribe(
                        { retrivedVideos ->
                            vidVideos = retrivedVideos
                            (vid_video_recycler.adapter as VidVideosAdapter).addVideos(retrivedVideos.videos)
                        },
                        { e ->
                            Snackbar.make(vid_video_recycler, e.message ?: "", Snackbar.LENGTH_LONG).show()
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
        val TAG = this.javaClass.simpleName


    }
}