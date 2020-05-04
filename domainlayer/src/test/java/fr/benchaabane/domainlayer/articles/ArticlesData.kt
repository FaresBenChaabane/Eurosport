package fr.benchaabane.domainlayer.articles

import org.threeten.bp.LocalDateTime

class ArticlesData {
    private val videoArticle = VideoArticle(title = "Dummy Title",
                                    thumbUrl = "Dummy Thumb",
                                    streamUrl = "Dummy Stream",
                                    sportName = "Dummy Sport",
                                    viewsNumber = 100,
                                    publishDate = LocalDateTime.MAX)

    private val storyArticle = StoryArticle(title = "Dummy Title",
                                    teaser = "Dummy Teaser",
                                    sportName = "Dummy Sport",
                                    authorName = "Dummy Author",
                                    publishDate = LocalDateTime.MAX,
                                    imageUrl = "Dummy Image",
                                    id = 1)

    val articles = Articles(videos = listOf(videoArticle),
                                stories = listOf(storyArticle))
}