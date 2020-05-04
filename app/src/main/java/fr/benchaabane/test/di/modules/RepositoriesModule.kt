package fr.benchaabane.test.di.modules

import dagger.Module
import dagger.Provides
import fr.benchaabane.datalayer.articles.ArticlesApiService
import fr.benchaabane.datalayer.articles.ArticlesRepositoryImp
import fr.benchaabane.domainlayer.articles.ArticlesRepository
import javax.inject.Singleton

@Module
class RepositoriesModule {

    @Provides
    @Singleton
    fun provideArticlesRepository(apiService: ArticlesApiService): ArticlesRepository {
        return ArticlesRepositoryImp(apiService)
    }
}