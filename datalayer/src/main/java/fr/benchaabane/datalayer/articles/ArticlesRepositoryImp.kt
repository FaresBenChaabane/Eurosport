package fr.benchaabane.datalayer.articles

import fr.benchaabane.domainlayer.articles.Articles
import fr.benchaabane.domainlayer.articles.ArticlesRepository
import fr.benchaabane.domainlayer.articles.StoryArticle
import io.reactivex.Single
import org.threeten.bp.ZoneOffset

class ArticlesRepositoryImp(private val apiService: ArticlesApiService) : ArticlesRepository {

    override fun fetchArticles(): Single<Articles> = apiService.getArticles().map { it.toArticles() }
        .doAfterSuccess { articles -> articles.stories.sortedBy { it.publishDate.toEpochSecond(
            ZoneOffset.UTC) } }
        .doAfterSuccess { articles -> articles.videos.sortedBy { it.publishDate.toEpochSecond(
            ZoneOffset.UTC) } }

    override fun fetchStoryDetails(storyId: Int): Single<StoryArticle> = apiService.getArticles()
        .map { it.stories.first { story -> story.id == storyId}.toStoryArticle() }
}
