package fr.benchaabane.domainlayer.articles

import org.threeten.bp.LocalDateTime

data class VideoArticle(val title: String,
                        val thumbUrl: String,
                        val streamUrl: String,
                        val publishDate: LocalDateTime,
                        val sportName: String,
                        val viewsNumber: Int)

data class StoryArticle(val id: Int,
                        val title: String,
                        val teaser: String,
                        val imageUrl: String,
                        val publishDate: LocalDateTime,
                        val authorName: String,
                        val sportName: String)

data class Articles(val videos: List<VideoArticle>,
                    val stories: List<StoryArticle>)