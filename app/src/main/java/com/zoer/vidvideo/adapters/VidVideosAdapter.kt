package com.zoer.vidvideo.adapters

import android.support.annotation.LayoutRes
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.squareup.picasso.Picasso
import com.zoer.vidvideo.R
import com.zoer.vidvideo.models.VidVideoModel
import kotlinx.android.synthetic.main.vid_video_item.view.*

class VidVideosAdapter(val videos: ArrayList<VidVideoModel>) : RecyclerView.Adapter<VidVideosAdapter.VideoHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VideoHolder {
        val inflatedView = parent.inflate(R.layout.vid_video_item, false)
        return VideoHolder(inflatedView)
    }

    override fun getItemCount(): Int {
        return videos.count()
    }

    override fun onBindViewHolder(holder: VideoHolder, position: Int) {
        val item=videos[position]
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
            view.likes_count.text = video.likeCount.toString()
            Picasso.with(view.context).load(video.previewImgUrl).into(view.video_preview)
        }

        override fun onClick(p0: View?) {
            //TODO(Implement showing video from url model.videoUrl)
        }

    }

    fun ViewGroup.inflate(@LayoutRes layoutRes: Int, attachToRoot: Boolean = false): View {
        return LayoutInflater.from(context).inflate(layoutRes, this, attachToRoot)
    }
}
