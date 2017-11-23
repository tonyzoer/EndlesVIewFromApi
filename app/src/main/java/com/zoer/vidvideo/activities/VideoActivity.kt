package com.zoer.vidvideo.activities

import android.app.Activity
import android.net.Uri
import android.os.Bundle
import com.zoer.vidvideo.R
import kotlinx.android.synthetic.main.activity_video.*
import java.net.URI

class VideoActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_video)
        video_webview.loadUrl(intent.getStringExtra("complete_url")    )
    }
}
