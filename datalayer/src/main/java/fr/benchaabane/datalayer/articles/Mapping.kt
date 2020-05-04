package fr.benchaabane.datalayer.articles

import fr.benchaabane.commons.tools.toLocalDateTime
import fr.benchaabane.domainlayer.articles.Articles
import fr.benchaabane.domainlayer.articles.StoryArticle
import fr.benchaabane.domainlayer.articles.VideoArticle

internal fun VideoArticleJson.toVideoArticle() = VideoArticle(title = title?: "",
                                                            thumbUrl = thumb?: "",
                                                            streamUrl = url?: "",
                                                            publishDate = date.toLong().toLocalDateTime(),
                                                            sportName = sport.sportName?: "",
                                                            viewsNumber = views)

internal fun StoryArticleJson.toStoryArticle() = StoryArticle(title = title?: "",
                                                            teaser = teaser?: "",
                                                            imageUrl = image?: "",
                                                            publishDate = date.toLong().toLocalDateTime(),
                                                            authorName = author?: "",
                                                            sportName = sport.sportName?: "",
                                                            id = id)

internal fun ArticlesJson.toArticles() = Articles(videos = videos.map { it.toVideoArticle() },
                                                    stories = stories.map { it.toStoryArticle() })