package com.zoer.vidvideo.activities

import android.app.Activity
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import com.google.android.exoplayer2.ExoPlayerFactory
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory
import com.google.android.exoplayer2.source.ExtractorMediaSource
import com.google.android.exoplayer2.source.hls.HlsMediaSource
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector
import com.google.android.exoplayer2.ui.SimpleExoPlayerView
import com.google.android.exoplayer2.upstream.DataSource
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory
import com.google.android.exoplayer2.upstream.TransferListener
import com.google.android.exoplayer2.util.Util
import com.zoer.vidvideo.R
import kotlinx.android.synthetic.main.activity_video.*
import java.net.URI

class VideoActivity : Activity() {

    private lateinit var player: SimpleExoPlayer
    private var shouldAutoPlay: Boolean = false
    private lateinit var trackSelector: DefaultTrackSelector


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_video)
        shouldAutoPlay = true
        actionBar.hide()
    }

    override fun onStart() {
        super.onStart()
        if (Util.SDK_INT > 23) {
            initPlayer()
        }
    }

    override fun onResume() {
        super.onResume()
        if ((Util.SDK_INT <= 23)) {
            initPlayer()
        }
    }

    private fun initPlayer() {
        val simpleExoPlayerView = findViewById<SimpleExoPlayerView>(R.id.player_view)
        val bandwidthMeter = DefaultBandwidthMeter()
        val videoTrackSelectionFactory = AdaptiveTrackSelection.Factory(bandwidthMeter)
        val mediaDataSourceFactory = DefaultDataSourceFactory(this, Util.getUserAgent(this, "mediaPlayerSample"), bandwidthMeter as TransferListener<in DataSource>)
        val mediaSource = HlsMediaSource(Uri.parse(intent.getStringExtra("complete_url")),
                mediaDataSourceFactory, 0, null, null)

        simpleExoPlayerView?.requestFocus()
        trackSelector = DefaultTrackSelector(videoTrackSelectionFactory)

        player = ExoPlayerFactory.newSimpleInstance(this, trackSelector)

        simpleExoPlayerView?.player = player
        player.playWhenReady = shouldAutoPlay;
        player.prepare(mediaSource)
    }

    fun releasePlayer() {
        player.release()
        shouldAutoPlay = player.playWhenReady
    }

    override fun onPause() {
        super.onPause()
        if (Util.SDK_INT <= 23) {
            releasePlayer()
        }
    }

    override fun onStop() {
        super.onStop()
        if (Util.SDK_INT > 23) {
            releasePlayer()
        }
    }

}
