package fr.benchaabane.presentationlayer.ui.articles

import androidx.navigation.findNavController
import fr.benchaabane.presentationlayer.R
import fr.benchaabane.presentationlayer.tools.bundle
import fr.benchaabane.presentationlayer.ui.BaseActivity
import fr.benchaabane.presentationlayer.ui.articles.details.ARG_ARTICLE_STORY_ID
import fr.benchaabane.presentationlayer.ui.articles.listing.ArticlesListingFragment
import fr.benchaabane.presentationlayer.ui.articles.streaming.ARG_ARTICLE_VIDEO_URL

class ArticlesActivity : BaseActivity(R.layout.activity_article_listing),
    ArticlesListingFragment.Delegate {


    override fun playVideo(streamingUrl: String) {
        findNavController(R.id.nav_host).navigate(R.id.action_articlesListingFragment_to_videoStreamingFragment,
            bundle { putString(ARG_ARTICLE_VIDEO_URL, streamingUrl) })
    }

    override fun showStoryDetails(storyId: Int) {
        findNavController(R.id.nav_host).navigate(R.id.action_articlesListingFragment_to_articleDetailsFragment,
            bundle { putInt(ARG_ARTICLE_STORY_ID, storyId) })
    }

}