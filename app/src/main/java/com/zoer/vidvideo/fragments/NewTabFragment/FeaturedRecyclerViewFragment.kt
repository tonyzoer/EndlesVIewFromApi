package com.zoer.vidvideo.fragments.NewTabFragment

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast

import com.zoer.vidvideo.R
import com.zoer.vidvideo.adapters.VidVideosAdapter
import com.zoer.vidvideo.commons.extensions.inflate
import com.zoer.vidvideo.features.InfiniteScrollListener
import com.zoer.vidvideo.models.VidVideoModel
import com.zoer.vidvideo.models.VidVideosModel
import kotlinx.android.synthetic.main.fragment_new_recycler_view.*
import kotlinx.android.synthetic.main.fragment_new_recycler_view.view.*
import rx.schedulers.Schedulers

class FeaturedRecyclerViewFragment : Fragment() {
    private val videosManager by lazy { FeaturedVidVideosManager() }
    private var vidVideos: VidVideosModel? = null  //IMPORTANT

    private var mListener: OnFragmentInteractionListener? = null
    //    private var linearLayoutManager: LinearLayoutManager = LinearLayoutManager(context)
    private lateinit var adapter: VidVideosAdapter
    private var videosArrayList: ArrayList<VidVideoModel> = ArrayList()

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        new_vid_video_recycler.setHasFixedSize(true)
        new_vid_video_recycler.layoutManager = LinearLayoutManager(context)
        new_vid_video_recycler.clearOnScrollListeners()
        new_vid_video_recycler.addOnScrollListener(InfiniteScrollListener({ requestFeaturedVideo() }, new_vid_video_recycler.layoutManager as LinearLayoutManager))
        initAdapter()
        if (savedInstanceState == null) {
            requestFeaturedVideo()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return container?.inflate(R.layout.fragment_new_recycler_view)
    }

    // TODO: Rename method, update argument and hook method into UI event
    fun onButtonPressed(uri: Uri) {
        if (mListener != null) {
            mListener!!.onFragmentInteraction(uri)
        }
    }

    public override fun onAttach(context: Context?) {
        super.onAttach(context)
        if (context is OnFragmentInteractionListener) {
            mListener = context as OnFragmentInteractionListener?
        } else {
            throw RuntimeException((context!!.toString() + " must implement OnFragmentInteractionListener")) as Throwable
        }
    }

    public override fun onDetach() {
        super.onDetach()
        mListener = null
    }

    private fun requestFeaturedVideo() {
        val subscription = videosManager.getFeatured(vidVideos?.getAfter() ?: 0)
                .subscribeOn(Schedulers.io())
                .subscribe(
                        { retrivedVideos ->
                            vidVideos = retrivedVideos
                            (new_vid_video_recycler.adapter as VidVideosAdapter).addVideos(retrivedVideos.videos)
                        },
                        { e ->
                            Snackbar.make(new_vid_video_recycler, e.message ?: "", Snackbar.LENGTH_LONG).show()
                        }
                )
    }

    private fun initAdapter() {
        if (new_vid_video_recycler.adapter == null) {
            new_vid_video_recycler.adapter = VidVideosAdapter()
        }
    }

    interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        fun onFragmentInteraction(uri: Uri)
    }

    companion object {
        // TODO: Rename parameter arguments, choose names that match

        private val ARG_PARAM1 = "param1"
        private val ARG_PARAM2 = "param2"


        // TODO: Rename and change types and number of parameters
        fun newInstance(param1: String, param2: String): FeaturedRecyclerViewFragment {
            val fragment = FeaturedRecyclerViewFragment()
            val args = Bundle()

            //params maybe

            fragment.setArguments(args)
            return fragment
        }
    }
}
