package com.zoer.vidvideo.adapters

import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import android.view.ViewGroup
import com.zoer.vidvideo.R
import com.zoer.vidvideo.commons.extensions.inflate
import com.zoer.vidvideo.commons.extensions.loadImg
import com.zoer.vidvideo.models.VidVideoModel
import com.zoer.vidvideo.models.VidVideosModel
import kotlinx.android.synthetic.main.vid_video_item.view.*

class VidVideosAdapter() : RecyclerView.Adapter<VidVideosAdapter.VideoHolder>() {
    private var videos: ArrayList<VidVideoModel> = ArrayList()

    fun addVideos(videosList: List<VidVideoModel>) {
        Log.d("LUULTEST", "$videosList added ")
        val initPosition = videos.size - 1
        videos.addAll(videosList)
        notifyItemRangeChanged(initPosition, videos.size)
        notifyDataSetChanged()
    }

    fun getVideos(): List<VidVideoModel> {
        return videos
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VideoHolder {
        val inflatedView = parent.inflate(R.layout.vid_video_item, false)
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

        override fun onClick(p0: View?) {
            //TODO(Implement showing video from url model.videoUrl)
        }


    }


}
