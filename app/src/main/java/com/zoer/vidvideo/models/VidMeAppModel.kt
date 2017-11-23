package com.zoer.vidvideo.models

data class VidMeAppModel(
        val application_id: Int,
        val client_id: String,
        val name: String,
        val website: String,
        val description: String,
        val organization: String,
        val redirect_uri_prefix: String
)