package com.zoer.vidvideo.api

import java.util.*

data class VidMeVideosResponse(val status: Boolean, val page: Page, val videos: List<Video>)


data class Page(val total: Int, val limit: Int, val offset: Int)

data class Video(val video_id: String, val complete_url: String, val complete: String, val title: String, val thumbnail: String, val score: Int)

data class VidMeAplicationResponse(val status: Boolean, val application: Application)

data class VidMeUserResponse(val status: Boolean, val auth: Auth, val user: User)

data class User(val user_id: String, val username: String, val full_url: String, val avatar_url: String)

data class Auth(val token: String, val expires: String, val user_id: String)

data class Application(val application_id: Int, val client_id: String, val name: String, val website: String, val description: String, val organization: String, val redirect_uri_prefix: String)