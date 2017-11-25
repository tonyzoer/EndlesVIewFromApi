package com.zoer.vidvideo.adapters

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.zoer.vidvideo.R
import com.zoer.vidvideo.commons.extensions.inflate
import com.zoer.vidvideo.commons.extensions.loadImg
import com.zoer.vidvideo.models.VidVideoModel
import kotlinx.android.synthetic.main.item_vid_video.view.*

class VidVideosAdapter() : RecyclerView.Adapter<VidVideosAdapter.VideoHolder>() {
    private var videos: ArrayList<VidVideoModel> = ArrayList()

    fun addVideos(videosList: List<VidVideoModel>) {
        val initPosition = videos.size - 1
        videos.addAll(videosList)
        notifyItemRangeChanged(initPosition, videos.size)
        notifyDataSetChanged()
    }

    fun getVideos(): List<VidVideoModel> {
        return videos
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VideoHolder {
        val inflatedView = parent.inflate(R.layout.item_vid_video, false)
        return VideoHolder(inflatedView)
    }

    override fun getItemCount(): Int {
        return videos.count()
    }

    override fun onBindViewHolder(holder: VideoHolder, position: Int) {
        val item = videos[position]
        holder.bindVidVideoModel(item)
    }


    class VideoHolder(v: View) : RecyclerView.ViewHolder(v), View.OnClickListener {
        private var model: VidVideoModel? = null
        private var view = v

        init {
            view.setOnClickListener(this)
        }

        fun bindVidVideoModel(video: VidVideoModel) {
            model = video
            view.title.text = video.title
            view.likes_count.text = video.score.toString()
            view.video_preview.loadImg("https://api.vid.me/video/${video.id}/thumbnail")
        }

        override fun onClick(p0: View) {

        }


    }



}
