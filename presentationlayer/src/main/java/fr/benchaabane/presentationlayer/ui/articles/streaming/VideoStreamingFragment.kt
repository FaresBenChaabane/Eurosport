package fr.benchaabane.presentationlayer.ui.articles.streaming

import android.content.Context
import android.net.Uri
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.source.MediaSource
import com.google.android.exoplayer2.source.ProgressiveMediaSource
import com.google.android.exoplayer2.upstream.DataSource
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory
import com.google.android.exoplayer2.util.Util
import fr.benchaabane.presentationlayer.R
import fr.benchaabane.presentationlayer.ui.BaseFragment
import kotlinx.android.synthetic.main.fragment_video_streaming.*


class VideoStreamingFragment : BaseFragment(R.layout.fragment_video_streaming) {

    private lateinit var player: SimpleExoPlayer

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (requireActivity() as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun afterViewCreated(view: View) {
        player = SimpleExoPlayer.Builder(view.context).build()
        val dataSourceFactory: DataSource.Factory = DefaultDataSourceFactory(
            context,
            Util.getUserAgent(view.context, getString(R.string.app_name))
        )
        val videoSource: MediaSource = ProgressiveMediaSource.Factory(dataSourceFactory)
            .createMediaSource(Uri.parse(arguments!!.getString(ARG_ARTICLE_VIDEO_URL)))
        player.prepare(videoSource)
        view_video.player = player
    }

    override fun onPause() {
        super.onPause()
        player.release()
    }

    override fun onDetach() {
        super.onDetach()
        player.release()
        (requireActivity() as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(false)
    }
}
const val ARG_ARTICLE_VIDEO_URL = ":ARG_ARTICLE_VIDEO_URL"