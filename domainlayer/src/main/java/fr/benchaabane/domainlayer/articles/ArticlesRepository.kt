package fr.benchaabane.domainlayer.articles

import io.reactivex.Single

interface ArticlesRepository {
    fun fetchArticles(): Single<Articles>
    fun fetchStoryDetails(storyId: Int): Single<StoryArticle>
}