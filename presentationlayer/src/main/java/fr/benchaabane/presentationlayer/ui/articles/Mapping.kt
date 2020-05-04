package fr.benchaabane.presentationlayer.ui.articles

import fr.benchaabane.domainlayer.articles.Articles
import fr.benchaabane.domainlayer.articles.StoryArticle
import fr.benchaabane.domainlayer.articles.VideoArticle
import fr.benchaabane.presentationlayer.R
import fr.benchaabane.presentationlayer.ui.Resources
import org.threeten.bp.LocalDateTime.now
import org.threeten.bp.temporal.ChronoUnit

internal fun VideoArticle.toUi(resources: Resources) =
    VideoArticleUi(
        thumbnailUrl = thumbUrl,
        title = title,
        views = resources.getString(R.string.views, viewsNumber),
        streamUrl = streamUrl,
        sportName = sportName
    )

internal fun StoryArticle.toUi(resources: Resources) =
    StoryArticleUi(
        imageUrl = imageUrl,
        title = title,
        authorName = authorName,
        teaser = teaser,
        id = id,
        timeLapse = resources.getString(
            R.string.time_laps,
            ChronoUnit.DAYS.between(publishDate, now())
        ),
        sportName = sportName
    )

internal fun Articles.toUi(resources: Resources) =
    ArticlesUi(videosUi = videos.map {
        it.toUi(
            resources
        )
    },
        storiesUi = stories.map { it.toUi(resources) })
