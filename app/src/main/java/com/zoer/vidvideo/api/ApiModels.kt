package com.zoer.vidvideo.api

data class VidMeVideosResponse(val status: Boolean, val page: Page, val videos: List<Video>)


data class Page(val total: Int, val limit: Int, val offset: Int)

data class Video(val video_id: String, val url: String, val title: String, val thumbnail: String, val score: Int)