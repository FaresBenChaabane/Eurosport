package fr.benchaabane.datalayer.articles

import fr.benchaabane.domainlayer.articles.Articles
import fr.benchaabane.domainlayer.articles.StoryArticle
import fr.benchaabane.domainlayer.articles.VideoArticle
import org.threeten.bp.LocalDateTime

class ArticlesData {

    val json by lazy { Json() }
    val domain by lazy { Domain() }

    class Json {
        private val videoArticleJson = VideoArticleJson(
            title = "Dummy Title",
            thumb = "Dummy Thumb",
            url = "Dummy Stream",
            sport = SportTypeJson(sportName = "Dummy Sport"),
            views = 100,
            date = 1588222335.11)

        private val storyArticleJson = StoryArticleJson(title = "Dummy Title",
            teaser = "Dummy Teaser",
            sport = SportTypeJson(sportName = "Dummy Sport"),
            author = "Dummy Author",
            date = 1588222335.11,
            image = "Dummy Image",
            id = 1)

        val articles = ArticlesJson(videos = listOf(videoArticleJson),
            stories = listOf(storyArticleJson))
    }

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
}