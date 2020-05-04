package fr.benchaabane.presentationlayer.ui.articles

import fr.benchaabane.domainlayer.articles.Articles
import fr.benchaabane.domainlayer.articles.StoryArticle
import fr.benchaabane.domainlayer.articles.VideoArticle
import org.threeten.bp.Clock
import org.threeten.bp.Instant
import org.threeten.bp.LocalDateTime
import org.threeten.bp.ZoneId

class ArticlesData {

    val domain by lazy { Domain() }
    val ui by lazy { Ui() }

    class Domain {
        private val videoArticle = VideoArticle(title = "Dummy Title",
            thumbUrl = "Dummy Thumb",
            streamUrl = "Dummy Stream",
            sportName = "Dummy Sport",
            viewsNumber = 100,
            publishDate = LocalDateTime.of(2020, 4, 30, 4, 52, 15))

        private val storyArticle = StoryArticle(title = "Dummy Title",
            teaser = "Dummy Teaser",
            sportName = "Dummy Sport",
            authorName = "Dummy Author",
            publishDate = LocalDateTime.of(2020, 4, 30, 4, 52, 15),
            imageUrl = "Dummy Image",
            id = 1)

        val articles = Articles(videos = listOf(videoArticle),
            stories = listOf(storyArticle))
    }

    class Ui {

        private val videoArticleUi = VideoArticleUi(title = "Dummy Title",
            thumbnailUrl = "Dummy Thumb",
            streamUrl = "Dummy Stream",
            sportName = "Dummy Sport",
            views = "100 views")

        private val storyArticleUi = StoryArticleUi(
            title = "Dummy Title",
            teaser = "Dummy Teaser",
            sportName = "Dummy Sport",
            authorName = "Dummy Author",
            timeLapse = "3 day(s) ago",
            imageUrl = "Dummy Image",
            id = 1
        )

        val articles = ArticlesUi(listOf(videoArticleUi), listOf(storyArticleUi))
    }
}
val NOW_CLOCK: Clock = Clock.fixed(Instant.parse("2018-12-03T10:15:30.000Z"), ZoneId.of("UTC"))