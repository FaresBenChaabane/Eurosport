package fr.benchaabane.test.di.modules

import dagger.Module
import dagger.Provides
import fr.benchaabane.domainlayer.articles.GetStoryDetailsUseCase
import fr.benchaabane.domainlayer.articles.RetrieveArticlesUseCase
import fr.benchaabane.presentationlayer.ui.Resources
import fr.benchaabane.presentationlayer.ui.articles.details.IStoryDetailsViewModel
import fr.benchaabane.presentationlayer.ui.articles.details.StoryDetailsViewModel
import fr.benchaabane.presentationlayer.ui.articles.listing.ArticlesListingViewModel
import fr.benchaabane.presentationlayer.ui.articles.listing.IArticlesListingViewModel

@Module
class ViewModelsModule {

    @Provides
    fun provideArticlesListingViewModel(
        useCase: RetrieveArticlesUseCase,
        resources: Resources
    ): IArticlesListingViewModel {
        return ArticlesListingViewModel(useCase, resources)
    }

    @Provides
    fun provideStoryDetailsViewModel(
        useCase: GetStoryDetailsUseCase,
        resources: Resources
    ): IStoryDetailsViewModel {
        return StoryDetailsViewModel(useCase, resources)
    }
}