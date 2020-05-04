package fr.benchaabane.test.di.modules

import dagger.Module
import dagger.Provides
import fr.benchaabane.domainlayer.articles.ArticlesRepository
import fr.benchaabane.domainlayer.articles.GetStoryDetailsUseCase
import fr.benchaabane.domainlayer.articles.RetrieveArticlesUseCase

@Module
class UseCasesModule {

    @Provides
    fun provideRetrieveArticlesUseCase(repository: ArticlesRepository): RetrieveArticlesUseCase {
        return RetrieveArticlesUseCase(repository)
    }

    @Provides
    fun provideGetStoryDetailsUseCase(repository: ArticlesRepository): GetStoryDetailsUseCase {
        return GetStoryDetailsUseCase(repository)
    }
}