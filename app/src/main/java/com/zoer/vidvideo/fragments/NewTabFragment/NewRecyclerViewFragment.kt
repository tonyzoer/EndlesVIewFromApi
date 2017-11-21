package com.zoer.vidvideo.fragments.NewTabFragment

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.zoer.vidvideo.R
import com.zoer.vidvideo.adapters.VidVideosAdapter
import com.zoer.vidvideo.models.VidVideoModel
import kotlinx.android.synthetic.main.fragment_new_recycler_view.*
import kotlinx.android.synthetic.main.fragment_new_recycler_view.view.*

class NewRecyclerViewFragment : Fragment() {


    private var mListener: OnFragmentInteractionListener? = null
//    private var linearLayoutManager: LinearLayoutManager = LinearLayoutManager(context)
    private lateinit var adapter: VidVideosAdapter
    private var videosArrayList: ArrayList<VidVideoModel> = ArrayList()
    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    public override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                                     savedInstanceState: Bundle?): View? {

        var root = inflater!!.inflate(R.layout.fragment_new_recycler_view, container, false)
//        if (root.new_vid_video_recycler.layoutManager != null)
            root.new_vid_video_recycler.layoutManager = LinearLayoutManager(context)
        adapter = VidVideosAdapter(videosArrayList)
        root.new_vid_video_recycler.adapter = adapter

        //TODO TEST(REMOVE)
        videosArrayList.add(VidVideoModel("1", "first", "https://static.pexels.com/photos/248797/pexels-photo-248797.jpeg", "https://static.pexels.com/photos/248797/pexels-photo-248797.jpeg", 134))
        videosArrayList.add(VidVideoModel("1", "first", "https://www.google.com/url?sa=i&rct=j&q=&esrc=s&source=images&cd=&cad=rja&uact=8&ved=0ahUKEwj04o-rtM7XAhXjIpoKHTp6BcIQjRwIBw&url=https%3A%2F%2Fwww.w3schools.com%2Fw3css%2Fw3css_images.asp&psig=AOvVaw3Dt5UE3tHK-kHEXFdkyYnG&ust=1511309631514826", "https://static.pexels.com/photos/248797/pexels-photo-248797.jpeg", 134))
        videosArrayList.add(VidVideoModel("1", "first", "https://www.google.com/url?sa=i&rct=j&q=&esrc=s&source=images&cd=&cad=rja&uact=8&ved=0ahUKEwiOmMy4tM7XAhUIG5oKHStZBHsQjRwIBw&url=https%3A%2F%2Fpixabay.com%2Fen%2Fheart-sweetheart-leaf-autumn-maple-1776746%2F&psig=AOvVaw3Dt5UE3tHK-kHEXFdkyYnG&ust=1511309631514826", "https://static.pexels.com/photos/248797/pexels-photo-248797.jpeg", 134))
        videosArrayList.add(VidVideoModel("1", "first", "https://www.bmw-eg.com/content/dam/bmw/common/all-models/4-series/gran-coupe/2017/images-and-videos/images/BMW-4-series-gran-coupe-images-and-videos-1920x1200-11.jpg.asset.1487328156349.jpg", "https://static.pexels.com/photos/248797/pexels-photo-248797.jpeg", 134))
        videosArrayList.add(VidVideoModel("1", "first", "https://static.pexels.com/photos/248797/pexels-photo-248797.jpeg", "https://static.pexels.com/photos/248797/pexels-photo-248797.jpeg", 134))
        videosArrayList.add(VidVideoModel("1", "first", "https://static.pexels.com/photos/248797/pexels-photo-248797.jpeg", "https://static.pexels.com/photos/248797/pexels-photo-248797.jpeg", 134))
        videosArrayList.add(VidVideoModel("1", "first", "https://static.pexels.com/photos/248797/pexels-photo-248797.jpeg", "https://static.pexels.com/photos/248797/pexels-photo-248797.jpeg", 134))
        adapter.notifyDataSetChanged()
        return root
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


    interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        fun onFragmentInteraction(uri: Uri)
    }

    companion object {
        // TODO: Rename parameter arguments, choose names that match

        private val ARG_PARAM1 = "param1"
        private val ARG_PARAM2 = "param2"


        // TODO: Rename and change types and number of parameters
        fun newInstance(param1: String, param2: String): NewRecyclerViewFragment {
            val fragment = NewRecyclerViewFragment()
            val args = Bundle()

            //params maybe

            fragment.setArguments(args)
            return fragment
        }
    }
}// Required empty public constructor
