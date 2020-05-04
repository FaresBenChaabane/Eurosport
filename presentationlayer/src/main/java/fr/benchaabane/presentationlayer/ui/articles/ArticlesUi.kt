package fr.benchaabane.presentationlayer.ui.articles

import fr.benchaabane.presentationlayer.R
import fr.benchaabane.presentationlayer.tools.views.MixedListAdapter

data class VideoArticleUi(val thumbnailUrl: String,
                          val title: String,
                          val views: String,
                          val streamUrl: String,
                          val sportName: String): MixedListAdapter.Item {
    override val layoutId = R.layout.item_video_holder
}

data class StoryArticleUi(val imageUrl: String,
                          val sportName: String,
                          val title: String,
                          val authorName: String,
                          val teaser: String,
                          val timeLapse: String,
                          val id: Int): MixedListAdapter.Item {
    override val layoutId = R.layout.item_story_holder
}

data class ArticlesUi(val videosUi: List<VideoArticleUi>,
                      val storiesUi: List<StoryArticleUi>)