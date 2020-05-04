package fr.benchaabane.datalayer.articles

import com.google.gson.annotations.SerializedName

data class SportTypeJson(@SerializedName("name") val sportName: String?)

data class VideoArticleJson(@SerializedName("title") val title: String?,
                            @SerializedName("thumb") val thumb: String?,
                            @SerializedName("url") val url: String?,
                            @SerializedName("date") val date: Double,
                            @SerializedName("sport") val sport: SportTypeJson,
                            @SerializedName("views") val views: Int)

data class StoryArticleJson(@SerializedName("id") val id: Int,
                            @SerializedName("title") val title: String?,
                            @SerializedName("teaser") val teaser: String?,
                            @SerializedName("image") val image: String?,
                            @SerializedName("date") val date: Double,
                            @SerializedName("author") val author: String?,
                            @SerializedName("sport")  val sport: SportTypeJson)

data class ArticlesJson(@SerializedName("videos") val videos: List<VideoArticleJson>,
                        @SerializedName("stories") val stories: List<StoryArticleJson>)